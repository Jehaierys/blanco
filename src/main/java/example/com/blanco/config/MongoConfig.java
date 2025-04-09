package example.com.blanco.config;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import org.bson.UuidRepresentation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration
@EnableMongoRepositories(basePackages = "example.com.blanco")
public class MongoConfig {

    @Bean
    public MongoTemplate mongoTemplate() {
        // Define the connection string
        ConnectionString connectionString = new ConnectionString("mongodb://admin:secret@blanco_mongo:27017/admin");

        // Build MongoClientSettings with the connection string and UUID representation
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)  // ✅ Set MongoDB URL
                .uuidRepresentation(UuidRepresentation.STANDARD)  // ✅ Fix UUID encoding issue
                .build();

        // Create MongoClient with settings
        var mongoClient = MongoClients.create(settings);

        // Return MongoTemplate
        return new MongoTemplate(mongoClient, "admin");
    }
}