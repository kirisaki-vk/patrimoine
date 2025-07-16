package school.hei.patrimoine.visualisation.web.service;

import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import school.hei.patrimoine.cas.ToutCas;
import school.hei.patrimoine.modele.objectif.ObjectifNonAtteint;

@Service
public class CasSetAnalyzerService {
  public void verify(ToutCas toutCas) {
    var objectifsNonAtteints = toutCas.verifier();
    if (!objectifsNonAtteints.isEmpty()) {
      throw new RuntimeException(
          "Objectifs non atteints : "
              + objectifsNonAtteints.stream()
                  .map(ObjectifNonAtteint::prettyPrint)
                  .collect(Collectors.joining("\n")));
    }
  }
}
