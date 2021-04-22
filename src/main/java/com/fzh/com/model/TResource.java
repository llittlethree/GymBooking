package com.fzh.com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@Table(name = "t_resource")
@Entity
public class TResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "path")
    private String path;

    @Column(name = "ref_id")
    private Long  refId;

    @Column(name = "ref_table")
    private String  refTable;

    @Column(name = "resource_code")
    private String  resourceCode;

    @Column(name = "use_status")
    private Integer  useStatus;

}
