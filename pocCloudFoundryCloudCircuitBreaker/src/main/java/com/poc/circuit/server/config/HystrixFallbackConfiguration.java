package com.poc.circuit.server.config;



import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;


@Configuration
public class HystrixFallbackConfiguration {
	private static final Logger LOGGER = Logger.getLogger(HystrixFallbackConfiguration.class);
	
	@Bean
    public FallbackProvider zuulFallbackProviderServiceEngine() {
        return new FallbackProvider() {
			@Override
			public String getRoute() {
				return "service-engine";
			}

			@Override
			public ClientHttpResponse fallbackResponse() {
				LOGGER.info(" *** fallbackResponse ***");
				return new FallbackClientHttpResponse("service-engine");	
			}

			@Override
			public ClientHttpResponse fallbackResponse(Throwable cause) {
				LOGGER.info(" *** fallbackResponse with cause ***");
				return new FallbackClientHttpResponse("service-engine",cause);
			}
        	
        };
	}   
	
	@Bean
    public FallbackProvider zuulFallbackProviderServiceWheel() {
        return new FallbackProvider() {
			@Override
			public String getRoute() {
				return "service-wheel";
			}

			@Override
			public ClientHttpResponse fallbackResponse() {
				LOGGER.info(" *** fallbackResponse ***");
				return new FallbackClientHttpResponse("service-wheel");	
			}

			@Override
			public ClientHttpResponse fallbackResponse(Throwable cause) {
				LOGGER.info(" *** fallbackResponse with cause ***");
				return new FallbackClientHttpResponse("service-wheel",cause);
			}
        	
        };
	}   
	
	
	public class FallbackClientHttpResponse implements ClientHttpResponse{
		private String serviceName = "";
		private Throwable cause = null;
		
		public FallbackClientHttpResponse( String serviceName) {
			this.serviceName = serviceName;
		}
		
		public FallbackClientHttpResponse( String serviceName, Throwable cause) {
			this.serviceName = serviceName;
			this.cause = cause;
		}
		
		@Override
		public HttpHeaders getHeaders() {
			HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return headers;
		}
		
		@Override
		public InputStream getBody() throws IOException {
			return new ByteArrayInputStream( ("{\"message\":\"Service "+serviceName+" Unavailable. Please try after sometime\"}" ).getBytes());
		}

		@Override
		public HttpStatus getStatusCode() throws IOException {
			return HttpStatus.OK;
		}

		@Override
		public int getRawStatusCode() throws IOException {
			return HttpStatus.OK.value();
		}

		@Override
		public String getStatusText() throws IOException {
			return HttpStatus.OK.toString();
		}

		@Override
		public void close() {
			//ignored
		}
		
	}
	
}
