package be.yapock.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User_ {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant unique de l'utilisateur
    @JoinColumn(referencedColumnName = "firstName", unique = true) @Column(nullable = false)
    private String lastName; // Nom de famille de l'utilisateur
    @Column(nullable = false)
    private String firstName; // Prénom de l'utilisateur
    @Column(unique = true, nullable = false)
    private String login; // Nom d'utilisateur unique
    @Column(nullable = false)
    private String password; // Mot de passe de l'utilisateur
    @Column(nullable = false)
    private LocalDate dateOfBirth; // Date de naissance de l'utilisateur

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        User_ user = (User_) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
