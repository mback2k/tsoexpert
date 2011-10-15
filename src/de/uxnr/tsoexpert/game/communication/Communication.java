package de.uxnr.tsoexpert.game.communication;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import de.uxnr.amf.AMF;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Communication {
	private static String[][] PACKAGE_MAPPINGS = new String[][] {
		{ "de.uxnr.tsoexpert.game.communication.vo",		"defaultGame.Communication.VO.d" },
		{ "de.uxnr.tsoexpert.game.communication.vo.guild",	"defaultGame.Communication.VO.Guild.d" },
		{ "de.uxnr.tsoexpert.game.communication.vo.mail",	"defaultGame.Communication.VO.Mail.d" },
		{ "de.uxnr.tsoexpert.game.communication.vo.update",	"defaultGame.Communication.VO.UpdateVO.d" }
	};

	public static void register() {
		List<Class> classes = null;

		for (String[] mapping : Communication.PACKAGE_MAPPINGS) {
			try {
				classes = Communication.getClassesForPackage(mapping[0]);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}

			String pckname = mapping[0] + ".";
			for (Class classVO : classes) {
				String className = classVO.getName().replace(pckname, mapping[1]);

				AMF.registerClass(className, classVO);
			}
		}
	}

	/**
	 * Attempts to list all the classes in the specified package as determined
	 * by the context class loader
	 * 
	 * @author http://stackoverflow.com/questions/1498122/java-loop-on-all-the-classes-in-the-classpath
	 * 
	 * @param pckgname the package name to search
	 * @return a list of classes that exist within that package
	 * @throws ClassNotFoundException if something went wrong
	 */
	private static List<Class> getClassesForPackage(String pckgname) throws ClassNotFoundException {
		// This will hold a list of directories matching the pckgname. There may
		// be more than one if a package is split over multiple jars/paths.
		ArrayList<File> directories = new ArrayList<File>();

		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			if (classLoader == null) {
				throw new ClassNotFoundException("Can't get class loader.");
			}

			String path = pckgname.replace('.', '/');

			// Ask for all resources for the path
			Enumeration<URL> resources = classLoader.getResources(path);

			while (resources.hasMoreElements()) {
				directories.add(new File(URLDecoder.decode(resources.nextElement().getPath(), "UTF-8")));
			}

		} catch (NullPointerException e) {
			throw new ClassNotFoundException(pckgname + " does not appear to be a valid package (Null pointer exception)");

		} catch (UnsupportedEncodingException e) {
			throw new ClassNotFoundException(pckgname + " does not appear to be a valid package (Unsupported encoding)");

		} catch (IOException e) {
			throw new ClassNotFoundException("IOException was thrown when trying to get all resources for " + pckgname);
		}

		ArrayList<Class> classes = new ArrayList<Class>();

		// For every directory identified capture all the .class files
		for (File directory : directories) {
			if (directory.exists()) {
				// Get the list of the files contained in the package
				String[] files = directory.list();

				for (String file : files) {
					// we are only interested in .class files
					if (file.endsWith(".class")) {
						// removes the .class extension
						try {
							classes.add(Class.forName(pckgname + '.' + file.substring(0, file.length() - 6)));

						} catch (NoClassDefFoundError e) {
							// do nothing. this class hasn't been found by the
							// loader, and we don't care.
						}
					}
				}

			} else {
				throw new ClassNotFoundException(pckgname + " (" + directory.getPath() + ") does not appear to be a valid package");
			}
		}

		return classes;
	}
}
