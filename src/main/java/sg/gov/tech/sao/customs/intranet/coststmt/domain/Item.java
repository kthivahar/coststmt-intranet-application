package sg.gov.tech.sao.customs.intranet.coststmt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Item.
 */
@Entity
@Table(name = "item")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description_of_goods")
    private String descriptionOfGoods;

    @Column(name = "hs_code")
    private String hsCode;

    @Column(name = "unit")
    private String unit;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "value")
    private Double value;

    @Column(name = "manufactring_cost_stmt_id")
    private String manufactringCostStmtId;

    @ManyToOne
    @JsonIgnoreProperties(value = "items", allowSetters = true)
    private ManufactringCostStmt manufactringCostStmtId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescriptionOfGoods() {
        return descriptionOfGoods;
    }

    public Item descriptionOfGoods(String descriptionOfGoods) {
        this.descriptionOfGoods = descriptionOfGoods;
        return this;
    }

    public void setDescriptionOfGoods(String descriptionOfGoods) {
        this.descriptionOfGoods = descriptionOfGoods;
    }

    public String getHsCode() {
        return hsCode;
    }

    public Item hsCode(String hsCode) {
        this.hsCode = hsCode;
        return this;
    }

    public void setHsCode(String hsCode) {
        this.hsCode = hsCode;
    }

    public String getUnit() {
        return unit;
    }

    public Item unit(String unit) {
        this.unit = unit;
        return this;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Item quantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getValue() {
        return value;
    }

    public Item value(Double value) {
        this.value = value;
        return this;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getManufactringCostStmtId() {
        return manufactringCostStmtId;
    }

    public Item manufactringCostStmtId(String manufactringCostStmtId) {
        this.manufactringCostStmtId = manufactringCostStmtId;
        return this;
    }

    public void setManufactringCostStmtId(String manufactringCostStmtId) {
        this.manufactringCostStmtId = manufactringCostStmtId;
    }

    public ManufactringCostStmt getManufactringCostStmtId() {
        return manufactringCostStmtId;
    }

    public Item manufactringCostStmtId(ManufactringCostStmt manufactringCostStmt) {
        this.manufactringCostStmtId = manufactringCostStmt;
        return this;
    }

    public void setManufactringCostStmtId(ManufactringCostStmt manufactringCostStmt) {
        this.manufactringCostStmtId = manufactringCostStmt;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Item)) {
            return false;
        }
        return id != null && id.equals(((Item) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Item{" +
            "id=" + getId() +
            ", descriptionOfGoods='" + getDescriptionOfGoods() + "'" +
            ", hsCode='" + getHsCode() + "'" +
            ", unit='" + getUnit() + "'" +
            ", quantity=" + getQuantity() +
            ", value=" + getValue() +
            ", manufactringCostStmtId='" + getManufactringCostStmtId() + "'" +
            "}";
    }
}
