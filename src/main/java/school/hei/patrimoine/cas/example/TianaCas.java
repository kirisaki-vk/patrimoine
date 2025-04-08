package school.hei.patrimoine.cas.example;

import static java.time.Month.*;
import static school.hei.patrimoine.modele.Argent.ariary;
import static school.hei.patrimoine.modele.Devise.MGA;

import java.time.LocalDate;
import java.util.Set;
import school.hei.patrimoine.cas.Cas;
import school.hei.patrimoine.modele.Devise;
import school.hei.patrimoine.modele.Personne;
import school.hei.patrimoine.modele.possession.*;

public class TianaCas extends Cas {
    private final Compte compteBancaire;

    public TianaCas() {
        super(LocalDate.of(2025, APRIL, 1), LocalDate.of(2026, MARCH, 31), new Personne("Tiana"));
        var au8Avril2025 = LocalDate.of(2025, APRIL, 8);
        compteBancaire = new Compte("Compte Bancaire", au8Avril2025, ariary(60_000_000));
    }

    @Override
    protected Devise devise() {
        return MGA;
    }

    @Override
    protected String nom() {
        return "Tiana cas";
    }

    @Override
    protected void init() {}

    @Override
    protected void suivi() {}

    @Override
    public Set<Possession> possessions() {
        var au8Avril2025 = LocalDate.of(2025, APRIL, 8);
        var terrain = new Materiel("Terrain", au8Avril2025, au8Avril2025, ariary(100_000_000), 0.1);
        var depensesMensuels = new FluxArgent("Depenses mensuels", compteBancaire, au8Avril2025, LocalDate.MAX, 1, ariary(-4_000_000));

        var au1Juin2025 = LocalDate.of(2025, JUNE, 1);
        var au31Decembre2025 = LocalDate.of(2025, DECEMBER, 31);
        var au1Mai2025 = LocalDate.of(2025, MAY, 1);
        var au27Juillet2025 = LocalDate.of(2025, JULY, 27);
        var dette = new Dette("Prêt Bancaire dette", au27Juillet2025, ariary(-24_000_000));
        var pretBancaire = new GroupePossession(
                "Pret bancaire",
                MGA,
                au27Juillet2025,
                Set.of(
                        dette,
                        new FluxArgent("Prêt Bancaire", compteBancaire, au27Juillet2025, ariary(20_000_000))
                )
        );

        var au27Aout2025 = LocalDate.of(2025, AUGUST, 27);
        var paymentDette = new GroupePossession(
            "Payment dette",
                MGA,
                au27Aout2025,
                Set.of(
                        new FluxArgent("Payment dette", dette, au27Juillet2025, au27Juillet2025.plusMonths(12), 27, ariary(2_000_000)),
                        new FluxArgent("Payment dette banque", compteBancaire, au27Juillet2025, au27Juillet2025.plusMonths(12), 27, ariary(-2_000_000))
                )
        );
        var projet = new GroupePossession(
                "Projet entreprenariale",
                MGA,
                au8Avril2025,
                Set.of(
                        new FluxArgent("Dépenses projet", compteBancaire, au1Juin2025, au31Decembre2025, 5, ariary(-5_000_000)),
                        new FluxArgent("Apport projet 10%", compteBancaire, au1Mai2025, ariary(7_000_000)),
                        new FluxArgent("Apport projet 90%", compteBancaire, au31Decembre2025.plusDays(1), ariary(63_000_000)),
                        pretBancaire,
                        paymentDette
                )
        );

        return Set.of(
                compteBancaire, terrain, projet, depensesMensuels);
    }
}
