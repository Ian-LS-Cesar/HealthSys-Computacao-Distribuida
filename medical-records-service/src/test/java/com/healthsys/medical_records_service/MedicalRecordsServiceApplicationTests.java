package com.healthsys.medical_records_service;

import com.healthsys.medical_records_service.client.BedClient;
import com.healthsys.medical_records_service.repository.ProntuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class MedicalRecordsServiceApplicationTests {

	@MockitoBean
	private ProntuarioRepository prontuarioRepository;

	@MockitoBean
	private BedClient bedClient;

	@Test
	void contextLoads() {
	}

}
