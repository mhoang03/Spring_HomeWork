package com.example.springweb.repository;

import com.example.springweb.entities.Customers;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository {
    private static final Logger logger = LoggerFactory.getLogger(CustomerRepository.class);
    private final List<Customers> customers = new ArrayList<>();
    private final Gson gson;
    private static final String FILE_PATH = "src/main/resources/data.json";

    public CustomerRepository(Gson gson) {
        this.gson = gson;
    }

    @PostConstruct
    public void init() {
        java.io.InputStream resource = getClass().getResourceAsStream("/CustomerData.json");
        if (resource == null) {
            logger.error("File CustomerData.json not found in resources");
            return;
        }

        try (Reader reader = new InputStreamReader(resource)) {
            List<Customers> loadedCustomers = gson.fromJson(reader, new TypeToken<List<Customers>>(){}.getType());
            if (loadedCustomers == null || loadedCustomers.isEmpty()) {
                logger.warn("No data loaded from CustomerData.json or data is empty");
            } else {
                customers.addAll(loadedCustomers);
                logger.info("Loaded {} customers from CustomerData.json", loadedCustomers.size());
                logger.debug("First customer ID: {}", loadedCustomers.get(0).getId());
            }
        } catch (Exception e) {
            logger.error("Failed to load data from CustomerData.json", e);
        }
    }

    public List<Customers> findAll() {
        return customers;
    }

    public void save(Customers customer) {
        // Tự động gán ID mới
        int newId = getNextId();
        customer.setId(newId);
        customers.add(customer);
        saveToFile();
    }

    private int getNextId() {
        if (customers.isEmpty()) {
            return 1; // Bắt đầu từ 1 nếu danh sách rỗng
        }
        // Lấy ID lớn nhất và tăng lên 1
        return customers.stream()
                .mapToInt(Customers::getId)
                .max()
                .orElse(0) + 1;
    }

    private void saveToFile() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .setPrettyPrinting()
                    .create();
            gson.toJson(customers, writer);
            logger.info("Saved customers to data.json");
        } catch (Exception e) {
            logger.error("Error saving customers to data.json", e);
        }
    }
}

// Class hỗ trợ serialize/deserialize LocalDate
class LocalDateAdapter implements com.google.gson.JsonSerializer<LocalDate>, com.google.gson.JsonDeserializer<LocalDate> {
    @Override
    public LocalDate deserialize(com.google.gson.JsonElement json, java.lang.reflect.Type typeOfT, com.google.gson.JsonDeserializationContext context) throws com.google.gson.JsonParseException {
        return LocalDate.parse(json.getAsString());
    }

    @Override
    public com.google.gson.JsonElement serialize(LocalDate src, java.lang.reflect.Type typeOfSrc, com.google.gson.JsonSerializationContext context) {
        return new com.google.gson.JsonPrimitive(src.toString());
    }
}