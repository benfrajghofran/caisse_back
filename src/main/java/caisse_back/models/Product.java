package caisse_back.models;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "T_PRODUCT",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        })
public class Product {
        @Id
        @GeneratedValue(strategy = IDENTITY)
        private Long id;

        @Column(name = "name")
        private String name;

        @Column(name = "description")
        private String description;

        @Column(name = "category")
        private String category;

        @Column(name = "price")
        private float price;

        @Column(name = "Image")
        private String image;


    }

