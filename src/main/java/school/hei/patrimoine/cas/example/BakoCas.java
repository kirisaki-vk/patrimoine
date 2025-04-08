package school.hei.patrimoine.cas.example;

import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static school.hei.patrimoine.modele.Argent.ariary;
import static school.hei.patrimoine.modele.Devise.MGA;

import java.time.LocalDate;
import java.util.Set;
import school.hei.patrimoine.cas.Cas;
import school.hei.patrimoine.modele.Devise;
import school.hei.patrimoine.modele.Personne;
import school.hei.patrimoine.modele.possession.*;

public class BakoCas extends Cas {
  private final Compte compteBNI;
  private final Compte compteBMOI;
  private final Compte coffreFort;

  public BakoCas() {
    super(LocalDate.of(2025, APRIL, 1), LocalDate.of(2025, DECEMBER, 31), new Personne("Bako"));
    var au8Avril2025 = LocalDate.of(2025, APRIL, 8);
    compteBNI = new Compte("Compte BNI", au8Avril2025, ariary(2_000_000));
    compteBMOI = new Creance("Compte BMOI", au8Avril2025, ariary(625_000));
    coffreFort = new Creance("Coffre fort", au8Avril2025, ariary(1_750_000));
  }

  @Override
  protected Devise devise() {
    return MGA;
  }

  @Override
  protected String nom() {
    return "Bako cas";
  }

  @Override
  protected void init() {}

  @Override
  protected void suivi() {}

  @Override
  public Set<Possession> possessions() {
    var au8Avril2025 = LocalDate.of(2025, APRIL, 8);
    var salaire =
        new FluxArgent("Salaire", compteBNI, au8Avril2025, LocalDate.MAX, 2, ariary(2_125_000));
    var epargne =
        new TransfertArgent(
            "Epargne", compteBNI, compteBMOI, au8Avril2025, LocalDate.MAX, 3, ariary(200_000));

    var loyer =
        new FluxArgent("Loyer", compteBNI, au8Avril2025, LocalDate.MAX, 26, ariary(-600_000));
    var trainDeVie =
        new FluxArgent("Train de vie", compteBNI, au8Avril2025, LocalDate.MAX, 1, ariary(-700_000));

    var ordinateurPortable =
        new Materiel("Ordinateur", au8Avril2025, au8Avril2025, ariary(3_000_000), -0.12);

    return Set.of(
        compteBNI, compteBMOI, coffreFort, salaire, epargne, loyer, trainDeVie, ordinateurPortable);
  }
}
