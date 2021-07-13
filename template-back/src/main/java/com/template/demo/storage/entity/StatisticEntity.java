package com.template.demo.storage.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "template_sc", name = "statistic")
@SequenceGenerator(name = "statistic_id_seq", sequenceName = "statistic_id_seq", allocationSize = 1)
public class StatisticEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "statistic_id_seq")
    private Integer id;

    @Column(name = "sent_time")
    private LocalDateTime time;

}
