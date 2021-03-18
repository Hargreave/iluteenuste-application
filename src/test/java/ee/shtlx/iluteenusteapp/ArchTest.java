package ee.shtlx.iluteenusteapp;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("ee.shtlx.iluteenusteapp");

        noClasses()
            .that()
            .resideInAnyPackage("ee.shtlx.iluteenusteapp.service..")
            .or()
            .resideInAnyPackage("ee.shtlx.iluteenusteapp.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..ee.shtlx.iluteenusteapp.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
