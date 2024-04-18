package org.disx;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "disx")
@AllArgsConstructor
@NoArgsConstructor
public class Disx {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "title", nullable = false, unique = true, length = 280)
    public String title;

    @Column(name = "content", nullable = false, length = 4000)
    public String content;
}
