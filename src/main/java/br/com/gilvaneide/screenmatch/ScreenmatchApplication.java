package br.com.gilvaneide.screenmatch;

import br.com.gilvaneide.screenmatch.service.ConsumoApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(ScreenmatchApplication.class, args);
	}

@Override
public void run(String... args) throws Exception {
	var consumoApi = new ConsumoApi();
	var json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=9de8e5d7");
	System.out.println(json);
	//var jsonCafeteria = consumoApi.obterDados("https://coffee.alexflipnote.dev/random.json");
	//System.out.println(jsonCafeteria);
}

}
