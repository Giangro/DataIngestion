package it.poste.ingestion;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import it.poste.ingestion.entity.NodoAllRiscontriCodiciRaccomandata;
import it.poste.ingestion.entity.NodoInputCodiciRaccomandata;
import it.poste.ingestion.repository.NodoAllRiscontriCodiciRaccomandataRepository;
import it.poste.ingestion.repository.NodoInputCodiciRaccomandataRepository;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ResourceUtils;

@SpringBootApplication
@Slf4j
public class IngestionApplication {

    @Value("${file}")
    String resourceFile;

    @Autowired
    private NodoInputCodiciRaccomandataRepository nodoInputCodiciRaccomandataRepository;

    @Autowired
    private NodoAllRiscontriCodiciRaccomandataRepository nodoAllRiscontriCodiciRaccomandataRepository;

    public static void main(String[] args) {
        SpringApplication.run(IngestionApplication.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {

            log.info("File is: {}", resourceFile);

            List<NodoAllRiscontriCodiciRaccomandata> listacodici
                    = new ArrayList<>();

            CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build(); // custom separator
            try (CSVReader reader = new CSVReaderBuilder(
                    new FileReader(loadFile()))
                    .withCSVParser(csvParser)
                    .build()) {

                String[] nextRecord;
                int counter;

                log.info("started processing...");

                for (counter = 0; (nextRecord = reader.readNext()) != null; counter++) {

                    //log.info("codice raccomandata    = {}", nextRecord[0]);                    
                    /*
                    nodoInputCodiciRaccomandataRepository.save(
                            NodoInputCodiciRaccomandata.builder()
                                .codiceRaccomandata(Long.parseLong(nextRecord[0]))
                                .build()
                    );
                     */
                    try {
                        listacodici.add(
                                NodoAllRiscontriCodiciRaccomandata.builder()
                                        .codiceRaccomandata(Long.parseLong(nextRecord[0]))
                                        .tipoRiscontro(nextRecord[1])
                                        .build());
                    } catch (NumberFormatException e) {
                        log.error("record skipped: bad codice raccomandata");
                    } // 
                    /*log.info("=====================");*/

                    if (((counter + 1) % 50000) == 0) {
                        log.info("loaded {} records", listacodici.size());
                    }

                }
                log.info("flushing: <{}>", listacodici.size());
                nodoAllRiscontriCodiciRaccomandataRepository.saveAll(listacodici);
                log.info("total records written: <{}>", listacodici.size());
            }

        };
    }

    private File loadFile()
            throws FileNotFoundException {
        return ResourceUtils.getFile(
                resourceFile);
    }

}
