package routines;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/*
 * user specification: the function's comment should contain keys as follows: 1. write about the function's comment.but
 * it must be before the "{talendTypes}" key.
 * 
 * 2. {talendTypes} 's value must be talend Type, it is required . its value should be one of: String, char | Character,
 * long | Long, int | Integer, boolean | Boolean, byte | Byte, Date, double | Double, float | Float, Object, short |
 * Short
 * 
 * 3. {Category} define a category for the Function. it is required. its value is user-defined .
 * 
 * 4. {param} 's format is: {param} <type>[(<default value or closed list values>)] <name>[ : <comment>]
 * 
 * <type> 's value should be one of: string, int, list, double, object, boolean, long, char, date. <name>'s value is the
 * Function's parameter name. the {param} is optional. so if you the Function without the parameters. the {param} don't
 * added. you can have many parameters for the Function.
 * 
 * 5. {example} gives a example for the Function. it is optional.
 */
public class SplitedFile {
	
	private static File destDir;
	private static BufferedReader reader;
	private static File file;

    /**
     * helloExample: not return value, only print "hello" + message.
     * 
     * 
     * {talendTypes} String
     * 
     * {Category} User Defined
     * 
     * {param} string("world") input: The string need to be printed.
     * 
     * {example} helloExemple("world") # hello world !.
     */
    public static void helloExample(String message) {
        if (message == null) {
            message = "World"; //$NON-NLS-1$
        }
        System.out.println("Hello " + message + " !"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    
    /**
     * helloExample: not return value, only print "hello" + message.
     * 
     * 
     * {talendTypes} String
     * 
     * {Category} User Defined
     * 
     * {param} string("world") input: The string need to be printed.
     * 
     * {example} helloExemple("world") # hello world !.
     */
    public static File[] splitFile(String filePath, String toDir)
    	    throws IOException {
    	    handleToDir(toDir);
    	    List<File> files = new ArrayList<File>();
    	    reader = new BufferedReader(new FileReader(filePath));
    	    String fileName = new File(filePath).getName();
    	    StringBuffer fileContent = new StringBuffer(); // un StringBuilder serait mieux
    	 
    	    reader.lines().forEach(l -> fileContent.append(l).append(System.lineSeparator())); // on lit toutes les lignes et on les concatène, avec la méthode #lines, apparue avec java 8
    	    /*Si on doit rester avec java 7 ou inférieur :
    	    String line;
    	    while ((line = reader.readLine()) != null) {
    	      fileContent.append(line);
    	    }*/
    	    String[] parts = fileContent.toString().split("P\\|"); // on découpe à chaque fois qu'on trouve "P|"
    	    for(int i = 1; i < parts.length;i++){ //on ignore la string à l'index 0, qui est vide (la partie situé avant le premier "P|")
    	      String part = "P|"+parts[i]; //on rajoute le "P|" enlevé lors du split
    	      File currentFile = createSplittedFile(fileName,i-1); //on utilise l'index -1 (pour partir de zéro) pour créer le nombre exact de fichiers
    	      writeFile(currentFile,part);
    	      files.add(currentFile);
    	    }
    	    return files.toArray(new File[files.size()]); // on utilise toArray, tout simplement...
    	  }
    
    /**
     * helloExample: not return value, only print "hello" + message.
     * 
     * 
     * {talendTypes} String
     * 
     * {Category} User Defined
     * 
     * {param} string("world") input: The string need to be printed.
     * 
     * {example} helloExemple("world") # hello world !.
     */
    public static void handleToDir(String toDir) {
		destDir = new File(toDir);
		if (destDir.exists())
			destDir.delete();
		destDir.mkdir();
	}
    
    /**
     * helloExample: not return value, only print "hello" + message.
     * 
     * 
     * {talendTypes} String
     * 
     * {Category} User Defined
     * 
     * {param} string("world") input: The string need to be printed.
     * 
     * {example} helloExemple("world") # hello world !.
     */
    public static File createSplittedFile(String fileName, int index) {
    	 
    	   File file=new File(destDir, fileName); // pas terrible, cette variable destDir statique !!!
    	   String name = file.getName(); 
    	   int pos = name.lastIndexOf("."); // détection de l'extension
    	   if( pos<0 ) {
    	      // pas d'extension
    	      name += "." + index;
    	   }
    	   else {
    	      name = name.substring(0,pos)+"."+index+name.substring(pos);
    	   }
    	   return new File(file.getParentFile(), name);
    }
    
    /**
     * helloExample: not return value, only print "hello" + message.
     * 
     * 
     * {talendTypes} String
     * 
     * {Category} User Defined
     * 
     * {param} string("world") input: The string need to be printed.
     * 
     * {example} helloExemple("world") # hello world !.
     */
    
    public static void writeFile(File destFile, String content)
			throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(destFile));
		writer.write(content);
		//System.out.println(content);
		writer.flush();
		writer.close();
		writer = null;
	}
    
}
