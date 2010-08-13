/**
* This is an attempt to svn update in all of the child directories of the directory that is passed in.
**/
public class SvnUpdaterUtil { 

    static void main(args) {
        println("Called main with ${args}")
        svnUpdate(Arrays.asList(args))
    }
    
    static String getPath(file) {
        return file.absolutePath
    }

    static void svnUpdate(dirs) {
        println("called with ${dirs}")
        
        for(dir in dirs) {
            println("dir: ${dir}")
            
            def files = new java.io.File(dir).listFiles()

            for (f in files) {
                def path = getPath(f)
		// A string can be executed in the standard java way:
		def command = """svn update ${path}"""// Create the String
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
}
