package com.learning.mockito;

/**
 * mock maker is turned off by default because it is based on completely different mocking mechanism that requires more feedback from the community. It can be activated explicitly by the mockito extension mechanism, just create in the classpath a file /mockito-extensions/org.mockito.plugins.MockMaker containing the value mock-maker-inline.
 * <p>
 * As a convenience, the Mockito team provides an artifact where this mock maker is preconfigured. Instead of using the mockito-core artifact, include the mockito-inline artifact in your project. Note that this artifact is likely to be discontinued once mocking of final classes and methods gets integrated into the default mock maker.
 * <p>
 * Some noteworthy notes about this mock maker:
 * <p>
 * Mocking final types and enums is incompatible with mock settings like :
 * explicitly serialization support withSettings().serializable()
 * extra-interfaces withSettings().extraInterfaces()
 * Some methods cannot be mocked
 * Package-visible methods of java.*
 * native methods
 * This mock maker has been designed around Java Agent runtime attachment ; this require a compatible JVM,
 * that is part of the JDK (or Java 9 VM). When running on a non-JDK VM prior to Java 9, it is however
 * possible to manually add the <a href="https://github.com/raphw/byte-buddy">Byte Buddy Java agent jar using the -javaagent parameter upon starting the JVM.
 *
 * If you are interested in more details of this feature please read the javadoc of org.mockito.internal.creation.bytebuddy.InlineByteBuddyMockMaker
 */
public class MockingFinalTypeEnumsAndFinalMethodsTest {

}
