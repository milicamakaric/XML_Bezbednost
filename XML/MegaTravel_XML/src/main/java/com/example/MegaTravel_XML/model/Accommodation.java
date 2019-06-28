//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.24 at 11:01:54 PM CEST 
//


package com.example.MegaTravel_XML.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="name">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="40"/>
 *               &lt;enumeration value=""/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element ref="{http://megatravel.com/address}address"/>
 *         &lt;element name="type" type="{http://megatravel.com/accommodation}accommodationType"/>
 *         &lt;element name="description">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="100"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="rating">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}double">
 *               &lt;minInclusive value="1"/>
 *               &lt;maxInclusive value="5"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="comments" type="{http://megatravel.com/accommodation}comment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="aditionalServices" type="{http://megatravel.com/accommodation}additionalService" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="image" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{http://megatravel.com/user}agent" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="cancelation" type="{http://megatravel.com/accommodation}cancelation"/>
 *         &lt;element ref="{http://megatravel.com/room}room" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "name",
    "address",
    "type",
    "description",
    "rating",
    "comments",
    "aditionalServices",
    "image",
    "agent",
    "cancelation"
})
@Entity
@XmlRootElement(name = "accommodation", namespace = "http://megatravel.com/accommodation")
@JsonIdentityInfo(scope = Accommodation.class,generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Accommodation implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    @XmlElement(required = true)
    protected String name;
    
    @OneToOne(cascade = {CascadeType.ALL})
    @XmlElement(namespace = "http://megatravel.com/address", required = true)
    protected Address address;
    
    @ManyToOne(cascade = {CascadeType.ALL})
    @XmlElement(required = true)
    protected AccommodationType type;
    
    @XmlElement(required = true)
    protected String description;
    
    protected double rating;
    
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL)
    protected List<Comment> comments;
    
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "accommodation_addservices",
            joinColumns = @JoinColumn(name = "accommodation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "additional_service_id", referencedColumnName = "id"))
    protected List<AdditionalService> aditionalServices;
    
    @XmlElement(required = true)
    protected String image;
    
    @LazyCollection(LazyCollectionOption.FALSE)
    @XmlElement(namespace = "http://megatravel.com/user")
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "accommodation_agent",
            joinColumns = @JoinColumn(name = "accommodation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "agent_id", referencedColumnName = "id"))
    protected List<Agent> agent;
    
    @XmlElement(required = true)
    @OneToOne
    protected Cancelation cancelation;
    

    //@LazyCollection(LazyCollectionOption.FALSE)
    //@XmlElement(namespace = "http://megatravel.com/room")
    //@OneToMany(mappedBy="accommodation")
    //@XmlTransient
    //protected List<Room> room;
    

    @XmlTransient
    protected int stars;

    /**
     * Gets the value of the id property.
     * 
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setAddress(Address value) {
        this.address = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link AccommodationType }
     *     
     */
    public AccommodationType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccommodationType }
     *     
     */
    public void setType(AccommodationType value) {
        this.type = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the rating property.
     * 
     */
    public double getRating() {
        return rating;
    }

    /**
     * Sets the value of the rating property.
     * 
     */
    public void setRating(double value) {
        this.rating = value;
    }

    /**
     * Gets the value of the comments property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comments property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Comment }
     * 
     * 
     */
    public List<Comment> getComments() {
        if (comments == null) {
            comments = new ArrayList<Comment>();
        }
        return this.comments;
    }

    /**
     * Gets the value of the aditionalServices property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aditionalServices property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAditionalServices().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdditionalService }
     * 
     * 
     */
    public List<AdditionalService> getAditionalServices() {
        if (aditionalServices == null) {
            aditionalServices = new ArrayList<AdditionalService>();
        }
        return this.aditionalServices;
    }
    
    public void setAditionalServices(List<AdditionalService> additional_services) {
		this.aditionalServices = additional_services;
	}

    /**
     * Gets the value of the image property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the value of the image property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImage(String value) {
        this.image = value;
    }

    /**
     * Gets the value of the agent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the agent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAgent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Agent }
     * 
     * 
     */
    public List<Agent> getAgent() {
        if (agent == null) {
            agent = new ArrayList<Agent>();
        }
        return this.agent;
    }
    
    public void setAgent(List<Agent> agent) {
		this.agent = agent;
	}

    /**
     * Gets the value of the cancelation property.
     * 
     * @return
     *     possible object is
     *     {@link Cancelation }
     *     
     */
    public Cancelation getCancelation() {
        return cancelation;
    }

    /**
     * Sets the value of the cancelation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Cancelation }
     *     
     */
    public void setCancelation(Cancelation value) {
        this.cancelation = value;
    }

    /**
     * Gets the value of the room property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the room property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoom().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Room }
     * 
     * 
     */

    /*
    public List<Room> getRoom() {
        if (room == null) {
            room = new ArrayList<Room>();
        }
        return this.room;
    }*/


	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}
    
    

}
