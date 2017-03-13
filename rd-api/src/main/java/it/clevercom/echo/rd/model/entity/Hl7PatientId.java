package it.clevercom.echo.rd.model.entity;
// Generated 13-mar-2017 9.59.07 by Hibernate Tools 5.2.2.Final


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Hl7PatientId generated by hbm2java
 */
@Embeddable
public class Hl7PatientId  implements java.io.Serializable {


     private String idhl7patient;
     private String idauthoritynamespaceid;

    public Hl7PatientId() {
    }

    public Hl7PatientId(String idhl7patient, String idauthoritynamespaceid) {
       this.idhl7patient = idhl7patient;
       this.idauthoritynamespaceid = idauthoritynamespaceid;
    }
   


    @Column(name="idhl7patient", nullable=false, length=16)
    public String getIdhl7patient() {
        return this.idhl7patient;
    }
    
    public void setIdhl7patient(String idhl7patient) {
        this.idhl7patient = idhl7patient;
    }


    @Column(name="idauthoritynamespaceid", nullable=false, length=20)
    public String getIdauthoritynamespaceid() {
        return this.idauthoritynamespaceid;
    }
    
    public void setIdauthoritynamespaceid(String idauthoritynamespaceid) {
        this.idauthoritynamespaceid = idauthoritynamespaceid;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Hl7PatientId) ) return false;
		 Hl7PatientId castOther = ( Hl7PatientId ) other; 
         
		 return ( (this.getIdhl7patient()==castOther.getIdhl7patient()) || ( this.getIdhl7patient()!=null && castOther.getIdhl7patient()!=null && this.getIdhl7patient().equals(castOther.getIdhl7patient()) ) )
 && ( (this.getIdauthoritynamespaceid()==castOther.getIdauthoritynamespaceid()) || ( this.getIdauthoritynamespaceid()!=null && castOther.getIdauthoritynamespaceid()!=null && this.getIdauthoritynamespaceid().equals(castOther.getIdauthoritynamespaceid()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getIdhl7patient() == null ? 0 : this.getIdhl7patient().hashCode() );
         result = 37 * result + ( getIdauthoritynamespaceid() == null ? 0 : this.getIdauthoritynamespaceid().hashCode() );
         return result;
   }   


}


