package it.clevercom.echo.rd.model.converter;

import org.dozer.CustomConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;

import it.clevercom.echo.rd.model.dto.AddressDTO;
import it.clevercom.echo.rd.model.dto.BaseObjectDTO;
import it.clevercom.echo.rd.model.dto.LocalityDTO;
import it.clevercom.echo.rd.model.dto.PatientDTO;
import it.clevercom.echo.rd.model.entity.Patient;

public class Patient2PatientDTO implements CustomConverter, MapperAware {
	@Autowired
	private Mapper rdDozerMapper;

	@Override
	public Object convert(Object destinationFieldValue, Object sourceFieldValue, Class<?> destinationClass,	Class<?> sourceClass) {
		if (sourceFieldValue == null) {
			return null;
		}
		
		if (sourceFieldValue instanceof Patient) {
			PatientDTO target = null; 
			Patient source = (Patient) sourceFieldValue;
			
			// check to see if the object already exists
			if (destinationFieldValue == null) {
				target = new PatientDTO();
			} else {
				target = (PatientDTO) destinationFieldValue;
			}
			
			// disclaimer: spaghetti code here
			
			target.setIdpatient((source.getIdpatient() != null) ? source.getIdpatient() : null);
			target.setName((source.getName() != null) ? source.getName() : null);
			target.setSurname((source.getSurname() != null) ? source.getSurname() : null);
			target.setDateofbirth((source.getDateofbirth() != null) ? source.getDateofbirth() : null);
			target.setDeathdate((source.getDeathdate() != null) ? source.getDeathdate() : null);
			target.setEmail((source.getEmail() != null) ? source.getEmail() : null);
			target.setGender((source.getGender() != null) ? source.getGender() : null);
			target.setHealthCode((source.getHealthcode() != null) ? source.getHealthcode() : null);
			target.setPhonenumber((source.getPhonenumber() != null) ? source.getPhonenumber() : null);
			target.setTaxcode((source.getTaxcode() != null) ? source.getTaxcode() : null);
			
			// map complex entities
			target.setCitizenship((source.getCitizenship() != null) ? new BaseObjectDTO(source.getCitizenship().getIdcitizenship().toString(), 
					source.getCitizenship().getDescription(), 
					null) : null);			
			
			target.setExtOrganizationUnit((source.getOrganizationUnitByIdextorganizationunit() != null) ? new BaseObjectDTO(source.getOrganizationUnitByIdextorganizationunit().getIdorganizationunit().toString(), 
					source.getOrganizationUnitByIdextorganizationunit().getName(), 
					source.getOrganizationUnitByIdextorganizationunit().getCode()) : null);
			
			target.setIntOrganizationUnit((source.getOrganizationUnitByIdintorganizationunit() != null) ? new BaseObjectDTO(source.getOrganizationUnitByIdintorganizationunit().getIdorganizationunit().toString(),
					source.getOrganizationUnitByIdintorganizationunit().getName(),
					source.getOrganizationUnitByIdintorganizationunit().getCode()) : null);
			
			target.setMaritalStatus((source.getMaritalstatus() != null) ? new BaseObjectDTO(source.getMaritalstatus().getIdmaritalstatus().toString(), 
					source.getMaritalstatus().getDescription(), 
					source.getMaritalstatus().getHl7code()) : null);		
			
			/**
			 * convert birthplace data
			 */
			BaseObjectDTO birthPlaceMunicipality = null;
			BaseObjectDTO birthPlaceProvince = null;
			BaseObjectDTO birthPlaceRegion = null;
			BaseObjectDTO birthPlaceCountry = null;
			if (source.getMunicipalityByBirthplaceidmunicipality()!=null) {
				 birthPlaceMunicipality = new BaseObjectDTO(source.getMunicipalityByBirthplaceidmunicipality().getIdmunicipality().toString(),
						source.getMunicipalityByBirthplaceidmunicipality().getMunicipalityname(),
						source.getMunicipalityByBirthplaceidmunicipality().getMunicipalitystdcode());
				 
				 birthPlaceProvince = new BaseObjectDTO(source.getMunicipalityByBirthplaceidmunicipality().getProvince().getIdprovince().toString(), 
						 source.getMunicipalityByBirthplaceidmunicipality().getProvince().getProvincename(), 
						 source.getMunicipalityByBirthplaceidmunicipality().getProvince().getProvincestdcode());
				 
				 birthPlaceRegion = new BaseObjectDTO(source.getMunicipalityByBirthplaceidmunicipality().getProvince().getRegion().getIdregion().toString(), 
						 source.getMunicipalityByBirthplaceidmunicipality().getProvince().getRegion().getRegionname().toString(), 
						 source.getMunicipalityByBirthplaceidmunicipality().getProvince().getRegion().getRegionstdcode().toString());
				 
				 birthPlaceCountry = new BaseObjectDTO(source.getMunicipalityByBirthplaceidmunicipality().getProvince().getRegion().getCountry().getIdcountry().toString(),
						 source.getMunicipalityByBirthplaceidmunicipality().getProvince().getRegion().getCountry().getCountrynicename().toString(),
						 source.getMunicipalityByBirthplaceidmunicipality().getProvince().getRegion().getCountry().getCountryisonumcode().toString());				 
			} else {
				if (source.getCountryByBirthplaceidcountry()!=null) {
					// birthplace country
					birthPlaceCountry = new BaseObjectDTO(source.getCountryByBirthplaceidcountry().getIdcountry().toString(), 
							source.getCountryByBirthplaceidcountry().getCountrynicename(),
							source.getCountryByBirthplaceidcountry().getCountryisonumcode().toString());
				}
			}

			LocalityDTO birthPlace = null;
			if ((birthPlaceCountry != null) || (birthPlaceMunicipality != null)) {
				birthPlace = new LocalityDTO(birthPlaceCountry, birthPlaceRegion, birthPlaceProvince, birthPlaceMunicipality);
			}
			
			/**
			 * convert residence data
			 */
			BaseObjectDTO residenceMunicipality = null;
			BaseObjectDTO residenceProvince = null;
			BaseObjectDTO residenceRegion = null;
			BaseObjectDTO residenceCountry = null;
			if (source.getMunicipalityByResidenceidmunicipality()!=null) {
				 residenceMunicipality = new BaseObjectDTO(source.getMunicipalityByResidenceidmunicipality().getIdmunicipality().toString(),
						source.getMunicipalityByResidenceidmunicipality().getMunicipalityname(),
						source.getMunicipalityByResidenceidmunicipality().getMunicipalitystdcode());
				 
				 residenceProvince = new BaseObjectDTO(source.getMunicipalityByResidenceidmunicipality().getProvince().getIdprovince().toString(), 
						 source.getMunicipalityByResidenceidmunicipality().getProvince().getProvincename(), 
						 source.getMunicipalityByResidenceidmunicipality().getProvince().getProvincestdcode());
				 
				 residenceRegion = new BaseObjectDTO(source.getMunicipalityByResidenceidmunicipality().getProvince().getRegion().getIdregion().toString(), 
						 source.getMunicipalityByResidenceidmunicipality().getProvince().getRegion().getRegionname().toString(), 
						 source.getMunicipalityByResidenceidmunicipality().getProvince().getRegion().getRegionstdcode().toString());
				 
				 residenceCountry = new BaseObjectDTO(source.getMunicipalityByResidenceidmunicipality().getProvince().getRegion().getCountry().getIdcountry().toString(),
						 source.getMunicipalityByResidenceidmunicipality().getProvince().getRegion().getCountry().getCountrynicename().toString(),
						 source.getMunicipalityByResidenceidmunicipality().getProvince().getRegion().getCountry().getCountryisonumcode().toString());				 
			} else {
				if (source.getCountryByResidenceidcountry()!=null) {
					// birthplace country
					residenceCountry = new BaseObjectDTO(source.getCountryByResidenceidcountry().getIdcountry().toString(), 
							source.getCountryByBirthplaceidcountry().getCountrynicename(),
							source.getCountryByBirthplaceidcountry().getCountryisonumcode().toString());
				}
			}
			
			AddressDTO residence = null;
			if ((residenceCountry != null) || (residenceMunicipality != null)) {
				residence = new AddressDTO(new LocalityDTO(residenceCountry, residenceRegion, residenceProvince, residenceMunicipality), source.getResidencestreetaddress());
			}
		
			/**
			 * convert domicile data
			 */
			BaseObjectDTO domicileMunicipality = null;
			BaseObjectDTO domicileProvince = null;
			BaseObjectDTO domicileRegion = null;
			BaseObjectDTO domicileCountry = null;
			if (source.getMunicipalityByDomicileidmunicipality()!=null) {
				domicileCountry = new BaseObjectDTO(source.getMunicipalityByDomicileidmunicipality().getIdmunicipality().toString(),
						source.getMunicipalityByDomicileidmunicipality().getMunicipalityname(),
						source.getMunicipalityByDomicileidmunicipality().getMunicipalitystdcode());
				 
				 domicileProvince = new BaseObjectDTO(source.getMunicipalityByDomicileidmunicipality().getProvince().getIdprovince().toString(), 
						 source.getMunicipalityByDomicileidmunicipality().getProvince().getProvincename(), 
						 source.getMunicipalityByDomicileidmunicipality().getProvince().getProvincestdcode());
				 
				 domicileRegion = new BaseObjectDTO(source.getMunicipalityByDomicileidmunicipality().getProvince().getRegion().getIdregion().toString(), 
						 source.getMunicipalityByDomicileidmunicipality().getProvince().getRegion().getRegionname().toString(), 
						 source.getMunicipalityByDomicileidmunicipality().getProvince().getRegion().getRegionstdcode().toString());
				 
				 domicileCountry = new BaseObjectDTO(source.getMunicipalityByDomicileidmunicipality().getProvince().getRegion().getCountry().getIdcountry().toString(),
						 source.getMunicipalityByDomicileidmunicipality().getProvince().getRegion().getCountry().getCountrynicename().toString(),
						 source.getMunicipalityByDomicileidmunicipality().getProvince().getRegion().getCountry().getCountryisonumcode().toString());				 
			} else {
				if (source.getCountryByDomicileidcountry()!=null) {
					// birthplace country
					domicileCountry = new BaseObjectDTO(source.getCountryByDomicileidcountry().getIdcountry().toString(), 
							source.getCountryByBirthplaceidcountry().getCountrynicename(),
							source.getCountryByBirthplaceidcountry().getCountryisonumcode().toString());
				}
			}			
			
			AddressDTO domicile = null;
			if ((domicileCountry != null) || (domicileMunicipality != null)) { 
				domicile = new AddressDTO(new LocalityDTO(domicileCountry, domicileRegion, domicileProvince, domicileMunicipality), source.getDomicilestreetaddress());
			}
		
			// inject locality and addresses data
			target.setBirthPlace(birthPlace);
			target.setResidence(residence);
			target.setDomicile(domicile);
			
			// inject tech fields
			target.setActive(source.getActive());
			target.setCreated(source.getCreated());
			target.setUpdated(source.getUpdated());
			target.setUserupdate(source.getUserupdate());
			
			// return adjusted PatientDTO
			return target.buildExtendedObject();
		} else if (sourceFieldValue instanceof PatientDTO) {
			Patient target = null; 
			PatientDTO source = (PatientDTO) sourceFieldValue;
			
			// check to see if the object already exists
			if (destinationFieldValue == null) {
				target = new Patient();
			} else {
				target = (Patient) destinationFieldValue;
			}
			
			return target;
		} else {
			throw new MappingException("Converter OrganizationUnit2BaseObjectDTO " + "used incorrectly. Arguments passed in were:" + destinationFieldValue + " and " + sourceFieldValue);
		}
	}

	@Override
	public void setMapper(Mapper mapper) {
		rdDozerMapper = mapper;
	}
}
