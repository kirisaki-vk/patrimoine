package school.hei.patrimoine.cas;

import static org.junit.jupiter.api.Assertions.*;
import static school.hei.patrimoine.conf.TestUtils.*;
import static school.hei.patrimoine.modele.Argent.ariary;

import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import school.hei.patrimoine.cas.example.BakoCas;

class CasSetAnalyzerTest {

  private CasSetAnalyzer analyzer;

  @BeforeEach
  void set_up() {
    analyzer = new CasSetAnalyzer();
  }

  @Test
  void cas_set_objective_achieved_ok() {
    assertDoesNotThrow(() -> analyzer.accept(casSet1()));
  }

  @Test
  void cas_set_objective_achieved_ko() {
    RuntimeException exception =
        assertThrows(RuntimeException.class, () -> analyzer.accept(casSet2()));
    assertTrue(exception.getMessage().contains("Objectifs non atteints"));
  }

  @Test
  void cas_set_bako_objectif_achieved_ok() {
    var casSetBako = new CasSet(Set.of(new BakoCas()), ariary(13_111_657));
    assertDoesNotThrow(() -> analyzer.accept(casSetBako));
  }
}
