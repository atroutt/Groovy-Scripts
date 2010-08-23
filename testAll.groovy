/**
* This is an attempt to run all of the ant test targets in the mfapps projects. It is expected that the project directories are in the directory that this script is run from.
**/
public class RunAllMFappsTestsUtil { 

    static void main(args) {
        runTests()
    }
    
    static String getPath(file) {
        return file.absolutePath
    }

    static void runTests() {
    	def files = Arrays.asList("jPuzzler", "fulfiller", "DirectoryServiceInterface", "MessagingCenter", "MessagingSystem", "UserViewer", "cas-war")

       	for (f in files) {
		// A string can be executed in the standard java way:
		def command = """ant -f  ${f}/build.xml test"""// Create the String
		println(command)
		def proc = command.execute()                 // Call *execute* on the string
		proc.waitFor()                               // Wait for the command to finish

		// Obtain status and output
		println "return code: ${ proc.exitValue()}"
		println "stderr: ${proc.err.text}"
		println "stdout: ${proc.in.text}" // *out* from the external program is *in* for groovy
      	} 
    }
}

