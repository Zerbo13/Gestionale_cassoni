package mattiazerbini.gestionale_cassoni.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String caricaFoto(MultipartFile foto) {

        try {

            Map risultato = cloudinary.uploader().upload(
                    foto.getBytes(),
                    ObjectUtils.emptyMap()
            );

            return risultato.get("secure_url").toString();

        } catch (IOException e) {

            throw new RuntimeException("Errore durante il caricamento della foto");
        }
    }
}