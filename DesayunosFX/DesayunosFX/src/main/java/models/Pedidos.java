package models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author USUARIO
 */

/**
 *
 * @author AlejandroVicenteJarn
 */
@Entity
@Table(name="pedidios")
public class Pedidos implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(name="cliente")
    private String cliente;
    @Column(name="curso")
    private String curso;
    @Column(name="fecha")
    private Date fecha;
    @Column(name="estado")
    private String estado;
    @Column (name="carta_id")
    private Integer cartaId;
    

    public Pedidos() {
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getCartaId() {
        return cartaId;
    }

    public void setCartaId(Integer cartaId) {
        this.cartaId = cartaId;
    }

    @Override
    public String toString() {
        return "pedidos{" + "id=" + id + ", cliente=" + cliente + ", curso=" + curso + ", fecha=" + fecha + ", estado=" + estado + ", cartaId=" + cartaId + '}';
    }
}