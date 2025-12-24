package config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CloudinaryConfig {
    private static final Cloudinary cloudinary;

    static {
        Properties properties = new Properties();
        try (InputStream input = CloudinaryConfig.class.getClassLoader().getResourceAsStream("cloudinary.properties")) {
            if (input == null) {
                throw new IOException("No se encontró el archivo cloudinary.properties");
            }
            // Cargar las propiedades desde el archivo
            properties.load(input);

            // Obtener las credenciales
            String cloudName = properties.getProperty("cloudinary.cloud_name");
            String apiKey = properties.getProperty("cloudinary.api_key");
            String apiSecret = properties.getProperty("cloudinary.api_secret");

            // Validar que las credenciales no sean nulas
            if (cloudName == null || apiKey == null || apiSecret == null) {
                throw new IllegalArgumentException("Faltan credenciales de Cloudinary en cloudinary.properties");
            }

            // Inicializar Cloudinary
            cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret
            ));
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar las credenciales de Cloudinary: " + e.getMessage(), e);
        }
    }

    public static Cloudinary getCloudinary() {
        return cloudinary;
    }
}