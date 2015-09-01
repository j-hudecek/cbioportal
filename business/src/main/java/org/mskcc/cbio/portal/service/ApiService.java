package org.mskcc.cbio.portal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mskcc.cbio.portal.model.DBCancerType;
import org.mskcc.cbio.portal.model.DBClinicalField;
import org.mskcc.cbio.portal.model.DBClinicalPatientData;
import org.mskcc.cbio.portal.model.DBClinicalSampleData;
import org.mskcc.cbio.portal.model.DBGene;
import org.mskcc.cbio.portal.model.DBGeneticAltRow;
import org.mskcc.cbio.portal.model.DBGeneticProfile;
import org.mskcc.cbio.portal.model.DBMutationData;
import org.mskcc.cbio.portal.model.DBPatient;
import org.mskcc.cbio.portal.model.DBPatientList;
import org.mskcc.cbio.portal.model.DBProfileData;
import org.mskcc.cbio.portal.model.DBProfileDataCaseList;
import org.mskcc.cbio.portal.model.DBSample;
import org.mskcc.cbio.portal.model.DBSimpleProfileData;
import org.mskcc.cbio.portal.model.DBStudy;
import org.mskcc.cbio.portal.persistence.CancerTypeMapper;
import org.mskcc.cbio.portal.persistence.ClinicalDataMapper;
import org.mskcc.cbio.portal.persistence.ClinicalFieldMapper;
import org.mskcc.cbio.portal.persistence.GeneMapper;
import org.mskcc.cbio.portal.persistence.GeneticProfileMapper;
import org.mskcc.cbio.portal.persistence.PatientListMapper;
import org.mskcc.cbio.portal.persistence.PatientMapper;
import org.mskcc.cbio.portal.persistence.ProfileDataMapper;
import org.mskcc.cbio.portal.persistence.SampleMapper;
import org.mskcc.cbio.portal.persistence.StudyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abeshoua
 */
@Service
public class ApiService {

	@Autowired
	private CancerTypeMapper cancerTypeMapper;
	@Autowired
	private ClinicalDataMapper clinicalDataMapper;
	@Autowired
	private ClinicalFieldMapper clinicalFieldMapper;
	@Autowired
	private GeneMapper geneMapper;
	@Autowired
	private GeneticProfileMapper geneticProfileMapper;
	@Autowired
	private PatientListMapper patientListMapper;
	@Autowired
	private PatientMapper patientMapper;
	@Autowired
	private ProfileDataMapper profileDataMapper;
	@Autowired
	private SampleMapper sampleMapper;
	@Autowired
	private StudyMapper studyMapper;

	@Transactional
	public List<DBCancerType> getCancerTypes() {
		return cancerTypeMapper.getAllCancerTypes();
	}

	@Transactional
	public List<DBCancerType> getCancerTypes(List<String> cancer_type_ids) {
		return cancerTypeMapper.getCancerTypes(cancer_type_ids);
	}

	@Transactional
	public List<DBClinicalSampleData> getSampleClinicalData(String study_id) {
		return clinicalDataMapper.getSampleClinicalDataByStudy(study_id);
	}

	@Transactional
	public List<DBClinicalSampleData> getSampleClinicalData(String study_id, List<String> sample_ids) {
		return clinicalDataMapper.getSampleClinicalDataBySample(study_id, sample_ids);
	}

	@Transactional
	public List<DBClinicalPatientData> getPatientClinicalData(String study_id) {
		return clinicalDataMapper.getPatientClinicalDataByStudy(study_id);
	}

	@Transactional
	public List<DBClinicalPatientData> getPatientClinicalData(String study_id, List<String> patient_ids) {
		return clinicalDataMapper.getPatientClinicalDataByPatient(study_id, patient_ids);
	}

	@Transactional
	public List<DBClinicalField> getSampleClinicalAttributes() {
		return clinicalFieldMapper.getAllSampleClinicalFields();
	}

	@Transactional
	public List<DBClinicalField> getSampleClinicalAttributes(String study_id) {
		return clinicalFieldMapper.getSampleClinicalFieldsByStudy(study_id);
	}

	@Transactional
	public List<DBClinicalField> getSampleClinicalAttributes(String study_id, List<String> sample_ids) {
		return clinicalFieldMapper.getSampleClinicalFieldsBySample(study_id, sample_ids);
	}

	@Transactional
	public List<DBClinicalField> getPatientClinicalAttributes() {
		return clinicalFieldMapper.getAllPatientClinicalFields();
	}

	@Transactional
	public List<DBClinicalField> getPatientClinicalAttributes(String study_id) {
		return clinicalFieldMapper.getPatientClinicalFieldsByStudy(study_id);
	}

	@Transactional
	public List<DBClinicalField> getPatientClinicalAttributes(String study_id, List<String> patient_ids) {
		return clinicalFieldMapper.getPatientClinicalFieldsByPatient(study_id, patient_ids);
	}

	@Transactional
	public List<DBGene> getGenes() {
		return geneMapper.getAllGenes();
	}

	@Transactional
	public List<DBGene> getGenes(List<String> hugo_gene_symbols) {
		return geneMapper.getGenesByHugo(hugo_gene_symbols);
	}

	@Transactional
	public List<DBGeneticProfile> getGeneticProfiles() {
		return geneticProfileMapper.getAllGeneticProfiles();
	}

	@Transactional
	public List<DBGeneticProfile> getGeneticProfiles(String study_id) {
		return geneticProfileMapper.getGeneticProfilesByStudy(study_id);
	}

	@Transactional
	public List<DBGeneticProfile> getGeneticProfiles(List<String> genetic_profile_ids) {
		return geneticProfileMapper.getGeneticProfiles(genetic_profile_ids);
	}

	@Transactional
	public List<DBPatientList> getPatientLists() {
		List<DBPatientList> incomplete_lists = patientListMapper.getAllIncompletePatientLists();
		for (DBPatientList l: incomplete_lists) {
			l.patient_ids = patientListMapper.getList(l.id);
		}
		return incomplete_lists;
	}

	@Transactional
	public List<DBPatientList> getPatientLists(String study_id) {
		List<DBPatientList> incomplete_lists = patientListMapper.getIncompletePatientListsByStudy(study_id);
		for (DBPatientList l: incomplete_lists) {
			l.patient_ids = patientListMapper.getList(l.id);
		}
		return incomplete_lists;
	}

	@Transactional
	public List<DBPatientList> getPatientLists(List<String> patient_list_ids) {
		List<DBPatientList> incomplete_lists = patientListMapper.getIncompletePatientLists(patient_list_ids);
		for (DBPatientList l: incomplete_lists) {
			l.patient_ids = patientListMapper.getList(l.id);
		}
		return incomplete_lists;
	}

	@Transactional
	public List<DBPatient> getPatients(String study_id) {
		return patientMapper.getPatientsByStudy(study_id);
	}

	@Transactional
	public List<DBPatient> getPatients(String study_id, List<String> patient_ids) {
		return patientMapper.getPatients(study_id, patient_ids);
	}

	@Transactional
	public List<DBProfileData> getGeneticProfileData(List<String> genetic_profile_ids, List<String> genes) {
		return getGeneticProfileData(genetic_profile_ids, genes, null);
	}

	@Transactional
	public List<DBProfileData> getGeneticProfileData(List<String> genetic_profile_ids, List<String> genes, List<String> sample_ids) {
		List<DBGeneticProfile> profiles = getGeneticProfiles(genetic_profile_ids);
		List<String> mutation_profiles = new ArrayList<>();
		List<String> non_mutation_profiles = new ArrayList<>();
		for (DBGeneticProfile p : profiles) {
			if (p.genetic_alteration_type.equals("MUTATION_EXTENDED")) {
				mutation_profiles.add(p.id);
			} else {
				non_mutation_profiles.add(p.id);
			}
		}
		List<DBProfileData> ret = new ArrayList<>();
		if (!mutation_profiles.isEmpty()) {
			List<DBMutationData> to_add;
			if (sample_ids == null) {
				to_add = profileDataMapper.getMutationData(mutation_profiles, genes);
			} else {
				to_add = profileDataMapper.getMutationDataBySample(mutation_profiles, genes, sample_ids);
			}
			ret.addAll(to_add);
		}
		if (!non_mutation_profiles.isEmpty()) {
			List<DBGeneticAltRow> genetic_alt_rows = profileDataMapper.getGeneticAlterationRow(non_mutation_profiles, genes);
			List<DBProfileDataCaseList> ordered_sample_lists = profileDataMapper.getProfileCaseLists(non_mutation_profiles);
			Map<String, String> sample_map = new HashMap<>();
			for (DBProfileDataCaseList sample_list : ordered_sample_lists) {
				String[] list = sample_list.ordered_sample_list.split(",");
				String key_prefix = sample_list.genetic_profile_id + "~";
				for (int i = 0; i < list.length; i++) {
					if (!list[i].equals("")) {
						sample_map.put(key_prefix + i, list[i]);
					}
				}
			}
			for (DBGeneticAltRow row : genetic_alt_rows) {
				String[] values = row.values.split(",");
				String key_prefix = row.genetic_profile_id + "~";
				for (int i = 0; i < values.length; i++) {
					if (!values[i].equals("")) {
						DBSimpleProfileData datum = new DBSimpleProfileData();
						datum.genetic_profile_id = row.genetic_profile_id;
						datum.sample_id = sample_map.get(key_prefix + i);
						datum.study_id = row.study_id;
						datum.hugo_gene_symbol = row.hugo_gene_symbol;
						datum.entrez_gene_id = row.entrez_gene_id;
						datum.profile_data = values[i];
						ret.add(datum);
					}
				}
			}
		}
		return ret;
	}
	
	@Transactional
	public List<DBSample> getSamples(String study_id) {
		return sampleMapper.getSamplesByStudy(study_id);
	}

	@Transactional
	public List<DBSample> getSamples(String study_id, List<String> sample_ids) {
		return sampleMapper.getSamples(study_id, sample_ids);
	}
	
	@Transactional
	public List<DBStudy> getStudies() {
		return studyMapper.getAllStudies();
	}
	
	@Transactional
	public List<DBStudy> getStudies(List<String> study_ids) {
		return studyMapper.getStudies(study_ids);
	}
}
