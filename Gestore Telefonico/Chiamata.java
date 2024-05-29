import java.time.LocalDateTime;

public class Chiamata {
    public String numero;
    public LocalDateTime dataChiamata;
    int durata;

    @Override
    public String toString() {
        return String.format("%s, %s, %d", numero, dataChiamata.toString(), durata);
    }
}
