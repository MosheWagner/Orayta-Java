package xml;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/*
 * This class reads and writes any compatible class to and from XML.
 * To make a class compatible, simple add '@XmlRootElement' to the top of it,
 * 	and any value with a getter and setter will be included in the XML.
 * 
 * NOTE: Because Java sucks at generics, you must pass T.class to the constructor of ClassIO.
 */

public class ClassIO <T>
{
	JAXBContext context = null;

	public ClassIO(Class<T> typeParameterClass) throws JAXBException
	{
		// get instance of JAXBContext based on root class
		context = JAXBContext.newInstance(typeParameterClass);
	}
	
	public String classToString(T obj) throws JAXBException
	{
		ByteArrayOutputStream bs = null;
	    if (context != null)
	    {
		    // Marshall into XML
		    Marshaller marshaller = context.createMarshaller();
		    marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
		    
		    // Write to Stream
		    bs = new ByteArrayOutputStream();
		    marshaller.marshal(obj, bs);
	    }
		
		return bs.toString();
	}
	
	public void saveClassToFile(T obj, String filePath) throws JAXBException, IOException
	{
	    if (context != null)
	    {
		    // Marshall into XML
		    Marshaller marshaller = context.createMarshaller();
		    marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
		    
		    // Write to File
		    File f = new File(filePath);
		    marshaller.marshal(obj, f);
	    }
	}
	

	@SuppressWarnings("unchecked")
	public T readClassFromFile(String filePath) throws JAXBException, IOException
	{
		T newclass = null;
		
		Unmarshaller um = context.createUnmarshaller();
		
		//This shouldn't fail, We'll get an 'Unmarshaller'ing exception way before that can happen.
		newclass = (T) um.unmarshal(new FileReader(filePath));

	    return newclass;
	}
}
