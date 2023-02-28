package com.promigas_storage.entity;

import lombok.Data;

@Data
public class OpportunitiesEntity {
    private int unique_id;
    private String city;
    private CountryEntity idCountry;
    private SectorEntity idSector;
    private TypeContractEntity idContract;
    private String typeProject;
    private String projecTitle;
    private String date;
    private String coordinates;
    private String descrip;
    private String section;
    private int horizonOpe;
    private int horizonPre;
    private String poc;
    private float trmBase;
    private float trmFin;
    private String propCapexUsd;
    private String propCapexCop;
    private boolean financialAsset;
    private String codCountry;
}
