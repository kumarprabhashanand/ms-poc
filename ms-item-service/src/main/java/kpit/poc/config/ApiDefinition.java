package kpit.poc.config;


import io.swagger.annotations.SwaggerDefinition;
import io.swagger.jaxrs.Reader;
import io.swagger.jaxrs.config.ReaderListener;
import io.swagger.models.Swagger;
import io.swagger.models.auth.BasicAuthDefinition;


@SwaggerDefinition
public class ApiDefinition implements ReaderListener {
	public static final String BASIC_AUTH_SCHEME = "basicAuth";
	
	@Override
	public void beforeScan(Reader reader, Swagger swagger) {
	}
	
	@Override
	public void afterScan(Reader reader, Swagger swagger) {
		BasicAuthDefinition basicAuthDefinition = new BasicAuthDefinition();
		swagger.addSecurityDefinition(BASIC_AUTH_SCHEME, basicAuthDefinition);
	}
}