package Data;

import java.util.ArrayList;
import java.util.List;

public class FasesHistory {
    private String serviceTag;
    private List<String> fases;

    public FasesHistory(String serviceTag) {
        this.serviceTag = serviceTag;
        this.fases = new ArrayList<>();
    }

    public void agregarFase(String fase) {
        fases.add(fase);
    }

    public void agregarDetalle(String detalle) {
        if (!fases.isEmpty()) {
            int lastIndex = fases.size() - 1;
            String ultimaFase = fases.get(lastIndex);
            fases.set(lastIndex, ultimaFase + " | " + detalle);
        } else {
            fases.add(detalle);
        }
    }

    public String getHistorialCompleto() {
        return "Historial de " + serviceTag + ": " + String.join(" -> ", fases);
    }
}