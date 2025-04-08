package school.hei.patrimoine.cas;

import school.hei.patrimoine.cas.example.TianaCas;
import school.hei.patrimoine.modele.Argent;

import java.util.Set;
import java.util.function.Supplier;

import static school.hei.patrimoine.modele.Argent.ariary;

public class CasSetSupplier implements Supplier<CasSet> {
  @Override
  public CasSet get() {
    return new CasSet(
            Set.of(
                    new TianaCas()
            ),
            ariary(20_000_000)
    );
  }
}
