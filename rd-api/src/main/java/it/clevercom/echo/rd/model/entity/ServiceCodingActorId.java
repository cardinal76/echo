package it.clevercom.echo.rd.model.entity;
// Generated 7-mar-2017 22.12.30 by Hibernate Tools 5.2.2.Final


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ServiceCodingActorId generated by hbm2java
 */
@Embeddable
public class ServiceCodingActorId  implements java.io.Serializable {


     private Long idservice;
     private Long idcodingactor;

    public ServiceCodingActorId() {
    }

    public ServiceCodingActorId(Long idservice, Long idcodingactor) {
       this.idservice = idservice;
       this.idcodingactor = idcodingactor;
    }
   


    @Column(name="idservice", nullable=false)
    public Long getIdservice() {
        return this.idservice;
    }
    
    public void setIdservice(Long idservice) {
        this.idservice = idservice;
    }


    @Column(name="idcodingactor", nullable=false)
    public Long getIdcodingactor() {
        return this.idcodingactor;
    }
    
    public void setIdcodingactor(Long idcodingactor) {
        this.idcodingactor = idcodingactor;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ServiceCodingActorId) ) return false;
		 ServiceCodingActorId castOther = ( ServiceCodingActorId ) other; 
         
		 return ( (this.getIdservice()==castOther.getIdservice()) || ( this.getIdservice()!=null && castOther.getIdservice()!=null && this.getIdservice().equals(castOther.getIdservice()) ) )
 && ( (this.getIdcodingactor()==castOther.getIdcodingactor()) || ( this.getIdcodingactor()!=null && castOther.getIdcodingactor()!=null && this.getIdcodingactor().equals(castOther.getIdcodingactor()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getIdservice() == null ? 0 : this.getIdservice().hashCode() );
         result = 37 * result + ( getIdcodingactor() == null ? 0 : this.getIdcodingactor().hashCode() );
         return result;
   }   


}


