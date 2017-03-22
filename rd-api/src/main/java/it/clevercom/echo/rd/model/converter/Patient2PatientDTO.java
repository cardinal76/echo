package it.clevercom.echo.rd.model.converter;

import java.util.Date;

import org.dozer.CustomConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;

import it.clevercom.echo.rd.model.dto.AddressDTO;
import it.clevercom.echo.rd.model.dto.BaseObjectDTO;
import it.clevercom.echo.rd.model.dto.LocalityDTO;
import it.clevercom.echo.rd.model.dto.PatientDTO;
import it.clevercom.echo.rd.model.entity.Citizenship;
import it.clevercom.echo.rd.model.entity.Country;
import it.clevercom.echo.rd.model.entity.Maritalstatus;
import it.clevercom.echo.rd.model.entity.Municipality;
import it.clevercom.echo.rd.model.entity.OrganizationUnit;
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
			
			// map standard patient data
			target.setIdpatient((source.getIdpatient() != null) ? source.getIdpatient() : null);
			target.setName((source.getName() != null) ? source.getName() : null);
			target.setSurname((source.getSurname() != null) ? source.getSurname() : null);
			target.setDateofbirth((source.getDateofbirth() != null) ? Long.valueOf(source.getDateofbirth().getTime()) : null);
			target.setDeathdate((source.getDeathdate() != null) ? Long.valueOf(source.getDeathdate().getTime()) : null);
			target.setEmail((source.getEmail() != null) ? source.getEmail() : null);
			target.setGender((source.getGender() != null) ? source.getGender() : null);
			target.setHealthCode((source.getHealthcode() != null) ? source.getHealthcode() : null);
			target.setPhonenumber((source.getPhonenumber() != null) ? source.getPhonenumber() : null);
			target.setTaxcode((source.getTaxcode() != null) ? source.getTaxcode() : null);
			
			// map related complex object
			if (source.getCitizenship()!=null) target.setCitizenship(rdDozerMapper.map(source.getCitizenship(), BaseObjectDTO.class));
			if (source.getOrganizationUnitByIdextorganizationunit()!=null) target.setExtOrganizationUnit(rdDozerMapper.map(source.getOrganizationUnitByIdextorganizationunit(), BaseObjectDTO.class));
			if (source.getOrganizationUnitByIdintorganizationunit()!=null) target.setIntOrganizationUnit(rdDozerMapper.map(source.getOrganizationUnitByIdintorganizationunit(), BaseObjectDTO.class));
			if (source.getMaritalstatus()!=null) target.setMaritalStatus(rdDozerMapper.map(source.getMaritalstatus(), BaseObjectDTO.class));
			
			/**
			 * convert birthplace data
			 */
			BaseObjectDTO birthPlaceMunicipality = null;
			BaseObjectDTO birthPlaceProvince = null;
			BaseObjectDTO birthPlaceRegion = null;
			BaseObjectDTO birthPlaceCountry = null;
			if (source.getMunicipalityByBirthplaceidmunicipality()!=null) {
				birthPlaceMunicipality = rdDozerMapper.map(source.getMunicipalityByBirthplaceidmunicipality(), BaseObjectDTO.class);
				birthPlaceProvince = rdDozerMapper.map(source.getMunicipalityByBirthplaceidmunicipality().getProvince(), BaseObjectDTO.class);
				birthPlaceRegion = rdDozerMapper.map(source.getMunicipalityByBirthplaceidmunicipality().getProvince().getRegion(), BaseObjectDTO.class);
				birthPlaceCountry = rdDozerMapper.map(source.getMunicipalityByBirthplaceidmunicipality().getProvince().getRegion().getCountry(), BaseObjectDTO.class);
			} else {
				if (source.getCountryByBirthplaceidcountry()!=null) {
					// birthplace country
					birthPlaceCountry = rdDozerMapper.map(source.getCountryByBirthplaceidcountry(), BaseObjectDTO.class);
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
				residenceMunicipality = rdDozerMapper.map(source.getMunicipalityByResidenceidmunicipality(), BaseObjectDTO.class);
				residenceProvince = rdDozerMapper.map(source.getMunicipalityByResidenceidmunicipality().getProvince(), BaseObjectDTO.class);
				residenceRegion = rdDozerMapper.map(source.getMunicipalityByResidenceidmunicipality().getProvince().getRegion(), BaseObjectDTO.class);
				residenceCountry = rdDozerMapper.map(source.getMunicipalityByResidenceidmunicipality().getProvince().getRegion().getCountry(), BaseObjectDTO.class);
			} else {
				if (source.getCountryByResidenceidcountry()!=null) {
					// residence country
					birthPlaceCountry = rdDozerMapper.map(source.getCountryByResidenceidcountry(), BaseObjectDTO.class);
				}
			}
			
			AddressDTO residence = null;
			if ((residenceCountry != null) || (residenceMunicipality != null)) {
				residence = new AddressDTO(new LocalityDTO(residenceCountry, residenceRegion, residenceProvince, residenceMunicipality), (source.getResidencestreetaddress()!=null) ? source.getResidencestreetaddress() : null);
			}
		
			/**
			 * convert domicile data
			 */
			BaseObjectDTO domicileMunicipality = null;
			BaseObjectDTO domicileProvince = null;
			BaseObjectDTO domicileRegion = null;
			BaseObjectDTO domicileCountry = null;
			if (source.getMunicipalityByDomicileidmunicipality()!=null) {				 
				 domicileMunicipality = rdDozerMapper.map(source.getMunicipalityByDomicileidmunicipality(), BaseObjectDTO.class);
				 domicileProvince = rdDozerMapper.map(source.getMunicipalityByDomicileidmunicipality().getProvince(), BaseObjectDTO.class);
				 domicileRegion = rdDozerMapper.map(source.getMunicipalityByDomicileidmunicipality().getProvince().getRegion(), BaseObjectDTO.class);
				 domicileCountry = rdDozerMapper.map(source.getMunicipalityByDomicileidmunicipality().getProvince().getRegion().getCountry(), BaseObjectDTO.class);
			} else {
				if (source.getCountryByDomicileidcountry()!=null) {
					// birthplace country
					domicileCountry = rdDozerMapper.map(source.getCountryByDomicileidcountry(), BaseObjectDTO.class);
				}
			}			
			
			AddressDTO domicile = null;
			if ((domicileCountry != null) || (domicileMunicipality != null)) { 
				domicile = new AddressDTO(new LocalityDTO(domicileCountry, domicileRegion, domicileProvince, domicileMunicipality), (source.getDomicilestreetaddress()!=null) ? source.getDomicilestreetaddress() : null);
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
			
			// disclaimer: spaghetti code here
			
			// map standard patient data
			target.setIdpatient((source.getIdpatient() != null) ? source.getIdpatient() : null);
			target.setName((source.getName() != null) ? source.getName() : null);
			target.setSurname((source.getSurname() != null) ? source.getSurname() : null);
			target.setDateofbirth((source.getDateofbirth() != null) ? new Date(source.getDateofbirth()) : null);
			target.setDeathdate((source.getDeathdate() != null) ? new Date(source.getDeathdate()) : null);
			target.setEmail((source.getEmail() != null) ? source.getEmail() : null);
			target.setGender((source.getGender() != null) ? source.getGender() : null);
			target.setHealthcode((source.getHealthCode() != null) ? source.getHealthCode() : null);
			target.setPhonenumber((source.getPhonenumber() != null) ? source.getPhonenumber() : null);
			target.setTaxcode((source.getTaxcode() != null) ? source.getTaxcode() : null);
			
			// map related complex object
			if (source.getCitizenship()!=null) target.setCitizenship(rdDozerMapper.map(source.getCitizenship(), Citizenship.class));
			if (source.getExtOrganizationUnit()!=null) target.setOrganizationUnitByIdextorganizationunit(rdDozerMapper.map(source.getExtOrganizationUnit(), OrganizationUnit.class));
			if (source.getIntOrganizationUnit()!=null) target.setOrganizationUnitByIdintorganizationunit(rdDozerMapper.map(source.getIntOrganizationUnit(), OrganizationUnit.class));
			if (source.getMaritalStatus()!=null) target.setMaritalstatus(rdDozerMapper.map(source.getMaritalStatus(), Maritalstatus.class));
			
			/**
			 * convert birthplace data
			 */
			Municipality birthPlaceMunicipality = null;
			Country birthPlaceCountry = null;
			if (source.getBirthPlace()!=null) {
				if (source.getBirthPlace().getMunicipality()!=null) {
					birthPlaceMunicipality = rdDozerMapper.map(source.getBirthPlace().getMunicipality(), Municipality.class);
				}
				if (source.getBirthPlace().getCountry()!=null) {
					birthPlaceCountry = rdDozerMapper.map(source.getBirthPlace().getCountry(), Country.class);
				}
			}

			target.setCountryByBirthplaceidcountry(birthPlaceCountry);
			target.setMunicipalityByBirthplaceidmunicipality(birthPlaceMunicipality);
			
			/**
			 * convert residence data
			 */
			Municipality residenceMunicipality = null;
			Country residenceCountry = null;
			if (source.getResidence()!=null) {
				if (source.getResidence().getLocality().getMunicipality()!=null) {
					residenceMunicipality = rdDozerMapper.map(source.getResidence().getLocality().getMunicipality(), Municipality.class);
				}
				if (source.getResidence().getLocality().getCountry()!=null) {
					residenceCountry = rdDozerMapper.map(source.getResidence().getLocality().getCountry(), Country.class);
				}
			}

			target.setCountryByResidenceidcountry(residenceCountry);
			target.setMunicipalityByResidenceidmunicipality(residenceMunicipality);
			target.setResidencestreetaddress((source.getResidence().getStreet()!=null) ? source.getResidence().getStreet() : null);
			
			/**
			 * convert domicile data
			 */
			Municipality domicileMunicipality = null;
			Country domicileCountry = null;
			if (source.getDomicile()!=null) {
				if (source.getDomicile().getLocality().getMunicipality()!=null) {
					domicileMunicipality = rdDozerMapper.map(source.getDomicile().getLocality().getMunicipality(), Municipality.class);
				}
				if (source.getDomicile().getLocality().getCountry()!=null) {
					domicileCountry = rdDozerMapper.map(source.getDomicile().getLocality().getCountry(), Country.class);
				}
			}

			target.setCountryByDomicileidcountry(domicileCountry);
			target.setMunicipalityByDomicileidmunicipality(domicileMunicipality);
			target.setDomicilestreetaddress((source.getDomicile().getStreet()!=null) ? source.getDomicile().getStreet() : null);
			
			return target;
		} else {
			throw new MappingException("Converter Patient2PatientDTO " + "used incorrectly. Arguments passed in were:" + destinationFieldValue + " and " + sourceFieldValue);
		}
	}

	@Override
	public void setMapper(Mapper mapper) {
		rdDozerMapper = mapper;
	}
}
