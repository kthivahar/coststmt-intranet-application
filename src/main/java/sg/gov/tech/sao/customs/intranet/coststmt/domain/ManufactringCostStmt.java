package sg.gov.tech.sao.customs.intranet.coststmt.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ManufactringCostStmt.
 */
@Entity
@Table(name = "manufactring_cost_stmt")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ManufactringCostStmt implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "unique_entity_number_uen")
    private String uniqueEntityNumberUen;

    @Column(name = "address")
    private String address;

    @Column(name = "name_of_contact_person")
    private String nameOfContactPerson;

    @Column(name = "designation")
    private String designation;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "email")
    private String email;

    @Column(name = "exporter_company_name")
    private String exporterCompanyName;

    @Column(name = "exporter_address")
    private String exporterAddress;

    @Column(name = "eu_company_name")
    private String euCompanyName;

    @Column(name = "country")
    private String country;

    @Column(name = "radio_1")
    private String radio1;

    @OneToMany(mappedBy = "manufactringCostStmtId")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Item> items = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public ManufactringCostStmt companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUniqueEntityNumberUen() {
        return uniqueEntityNumberUen;
    }

    public ManufactringCostStmt uniqueEntityNumberUen(String uniqueEntityNumberUen) {
        this.uniqueEntityNumberUen = uniqueEntityNumberUen;
        return this;
    }

    public void setUniqueEntityNumberUen(String uniqueEntityNumberUen) {
        this.uniqueEntityNumberUen = uniqueEntityNumberUen;
    }

    public String getAddress() {
        return address;
    }

    public ManufactringCostStmt address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNameOfContactPerson() {
        return nameOfContactPerson;
    }

    public ManufactringCostStmt nameOfContactPerson(String nameOfContactPerson) {
        this.nameOfContactPerson = nameOfContactPerson;
        return this;
    }

    public void setNameOfContactPerson(String nameOfContactPerson) {
        this.nameOfContactPerson = nameOfContactPerson;
    }

    public String getDesignation() {
        return designation;
    }

    public ManufactringCostStmt designation(String designation) {
        this.designation = designation;
        return this;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getContactNo() {
        return contactNo;
    }

    public ManufactringCostStmt contactNo(String contactNo) {
        this.contactNo = contactNo;
        return this;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public ManufactringCostStmt email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExporterCompanyName() {
        return exporterCompanyName;
    }

    public ManufactringCostStmt exporterCompanyName(String exporterCompanyName) {
        this.exporterCompanyName = exporterCompanyName;
        return this;
    }

    public void setExporterCompanyName(String exporterCompanyName) {
        this.exporterCompanyName = exporterCompanyName;
    }

    public String getExporterAddress() {
        return exporterAddress;
    }

    public ManufactringCostStmt exporterAddress(String exporterAddress) {
        this.exporterAddress = exporterAddress;
        return this;
    }

    public void setExporterAddress(String exporterAddress) {
        this.exporterAddress = exporterAddress;
    }

    public String getEuCompanyName() {
        return euCompanyName;
    }

    public ManufactringCostStmt euCompanyName(String euCompanyName) {
        this.euCompanyName = euCompanyName;
        return this;
    }

    public void setEuCompanyName(String euCompanyName) {
        this.euCompanyName = euCompanyName;
    }

    public String getCountry() {
        return country;
    }

    public ManufactringCostStmt country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRadio1() {
        return radio1;
    }

    public ManufactringCostStmt radio1(String radio1) {
        this.radio1 = radio1;
        return this;
    }

    public void setRadio1(String radio1) {
        this.radio1 = radio1;
    }

    public Set<Item> getItems() {
        return items;
    }

    public ManufactringCostStmt items(Set<Item> items) {
        this.items = items;
        return this;
    }

    public ManufactringCostStmt addItem(Item item) {
        this.items.add(item);
        item.setManufactringCostStmt(this);
        return this;
    }

    public ManufactringCostStmt removeItem(Item item) {
        this.items.remove(item);
        item.setManufactringCostStmtId(null);
        return this;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ManufactringCostStmt)) {
            return false;
        }
        return id != null && id.equals(((ManufactringCostStmt) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ManufactringCostStmt{" +
            "id=" + getId() +
            ", companyName='" + getCompanyName() + "'" +
            ", uniqueEntityNumberUen='" + getUniqueEntityNumberUen() + "'" +
            ", address='" + getAddress() + "'" +
            ", nameOfContactPerson='" + getNameOfContactPerson() + "'" +
            ", designation='" + getDesignation() + "'" +
            ", contactNo='" + getContactNo() + "'" +
            ", email='" + getEmail() + "'" +
            ", exporterCompanyName='" + getExporterCompanyName() + "'" +
            ", exporterAddress='" + getExporterAddress() + "'" +
            ", euCompanyName='" + getEuCompanyName() + "'" +
            ", country='" + getCountry() + "'" +
            ", radio1='" + getRadio1() + "'" +
            "}";
    }
}
