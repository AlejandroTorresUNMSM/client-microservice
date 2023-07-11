package com.atorres.nttdata.client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public final class ClientMicroserviceApplication {
	/**.
	 * Funcion principal
	 * @param args arg
	 */
	public static void main(final String[] args) {
		SpringApplication.run(ClientMicroserviceApplication.class,
						args);
	}

	/**.
	 * Constructor privado
	 */
	private  ClientMicroserviceApplication() { }

}
