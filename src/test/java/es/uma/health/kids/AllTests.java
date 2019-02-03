package es.uma.health.kids;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import es.uma.health.kids.application.service.message.UpdateProposedAppointmentTest;
import es.uma.health.kids.application.service.message.UserProposeAppointmentTest;
import es.uma.health.kids.application.service.message.UserSendMessageTest;
import es.uma.health.kids.application.service.message.UserViewMessagesTest;
import es.uma.health.kids.application.service.user.UserViewTheirPatientsTest;
import es.uma.health.kids.application.service.user.doctor.DoctorAssignNewPatientTest;
import es.uma.health.kids.application.service.user.doctor.DoctorUnassignAPatientTest;
import es.uma.health.kids.application.service.user.patientresponsible.ResponsibleAddPatientTest;
import es.uma.health.kids.application.service.user.patientresponsible.ResponsibleDeletePatientTest;
import es.uma.health.kids.application.service.user.patientresponsible.ResponsibleUpdatePatientTest;

@RunWith(Suite.class)
@SuiteClasses({
	UserSendMessageTest.class,
	UserProposeAppointmentTest.class,
	UpdateProposedAppointmentTest.class,
	UserViewMessagesTest.class,
	ResponsibleAddPatientTest.class,
	ResponsibleUpdatePatientTest.class,
	ResponsibleDeletePatientTest.class,
	UserViewTheirPatientsTest.class,
	DoctorAssignNewPatientTest.class,
	DoctorUnassignAPatientTest.class
})
public class AllTests {}
