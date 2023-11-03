package be.yapock.dal.models.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant unique de la catégorie
    @Column(nullable = false,unique = true)
    private String name; // Nom de la catégorie, obligatoire et unique

    private LocalDate creationDate; // Date de création de la catégorie
    private LocalDate modificationDate; // Date de modification de la catégorie


    @ToString.Exclude
    @OneToMany(mappedBy = "category", orphanRemoval = true)
    private Set<Task> tasksId = new LinkedHashSet<>(); // Ensemble de tâches associées à cette catégorie

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "Category_users",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id"))
    private Set<User_> readOnlyUsers = new LinkedHashSet<>(); // Ensemble d'utilisateurs avec des droits de lecture sur cette catégorie

    @ToString.Exclude
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User_ user; // Utilisateur propriétaire de la catégorie

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Category category = (Category) o;
        return getId() != null && Objects.equals(getId(), category.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
