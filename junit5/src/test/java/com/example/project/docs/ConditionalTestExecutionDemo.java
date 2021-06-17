package com.example.project.docs;

import static org.junit.jupiter.api.condition.JRE.JAVA_10;
import static org.junit.jupiter.api.condition.JRE.JAVA_11;
import static org.junit.jupiter.api.condition.JRE.JAVA_8;
import static org.junit.jupiter.api.condition.JRE.JAVA_9;
import static org.junit.jupiter.api.condition.OS.LINUX;
import static org.junit.jupiter.api.condition.OS.MAC;
import static org.junit.jupiter.api.condition.OS.WINDOWS;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledForJreRange;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;

public class ConditionalTestExecutionDemo {
	@Test
	@EnabledOnOs(MAC)
	void onlyOnMacOs() {
		// ...
	}

	@TestOnMac
	void testOnMac() {
		// ...
	}

	@Test
	@EnabledOnOs({ LINUX, MAC })
	void onLinuxOrMac() {
		// ...
	}

	@Test
	@DisabledOnOs(WINDOWS)
	void notOnWindows() {
		// ...
	}

	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	@Test
	@EnabledOnOs(MAC)
	@interface TestOnMac {
	}

	@Test
	@EnabledOnJre(JAVA_8)
	void onlyOnJava8() {
		// ...
	}

	@Test
	@EnabledOnJre({ JAVA_9, JAVA_10 })
	void onJava9Or10() {
		// ...
	}

	@Test
	@EnabledForJreRange(min = JAVA_9, max = JAVA_11)
	void fromJava9to11() {
		// ...
	}

	@Test
	@EnabledForJreRange(min = JAVA_9)
	void fromJava9toCurrentJavaFeatureNumber() {
		// ...
	}

	@Test
	@EnabledForJreRange(max = JAVA_11)
	void fromJava8To11() {
		// ...
	}

	@Test
	@DisabledOnJre(JAVA_9)
	void notOnJava9() {
		// ...
	}

	@Test
	@DisabledForJreRange(min = JAVA_9, max = JAVA_11)
	void notFromJava9to11() {
		// ...
	}

	@Test
	@DisabledForJreRange(min = JAVA_9)
	void notFromJava9toCurrentJavaFeatureNumber() {
		// ...
	}

	@Test
	@DisabledForJreRange(max = JAVA_11)
	void notFromJava8to11() {
		// ...
	}

	@Test
	@EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
	void onlyOn64BitArchitectures() {
		// ...
	}

	@Test
	@DisabledIfSystemProperty(named = "ci-server", matches = "true")
	void notOnCiServer() {
		// ...
	}

	@Test
	@EnabledIfEnvironmentVariable(named = "ENV", matches = "staging-server")
	void onlyOnStagingServer() {
		// ...
	}

	@Test
	@DisabledIfEnvironmentVariable(named = "ENV", matches = ".*development.*")
	void notOnDeveloperWorkstation() {
		// ...
	}

	/**
	 * A container or test may be enabled or disabled based on the boolean return of
	 * a method via the @EnabledIf and @DisabledIf annotations. The method is
	 * provided to the annotation via its name, or its fully qualified name if
	 * located outside the test class. If needed, the condition method can take a
	 * single parameter of type ExtensionContext.
	 */

	/**
	 * When @EnabledIf or @DisabledIf is used at class level, the condition method
	 * must always be static. Condition methods located in external classes must
	 * also be static. In any other case, you can use both static or instance
	 * methods.
	 */

	@Test
	@EnabledIf("customCondition")
	void enabled() {
		// ...
	}

	@Test
	@DisabledIf("customCondition")
	void disabled() {
		// ...
	}

	boolean customCondition() {
		return true;
	}
}
