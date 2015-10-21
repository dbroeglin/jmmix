package fr.broeglin.jmmix.simulator;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: java -jar jmmix.jar <mmo file>");
			System.exit(-1);
		}

		Simulator simulator = new Simulator();

		try (DataInputStream dis = new DataInputStream(new FileInputStream(
				args[0]))) {
			Loader loader = new Loader(dis, simulator);

			loader.load();
		} catch (FileNotFoundException e) {
			System.err.println("Unable to find file '" + args[0] + "'");
			System.exit(-1);
		} catch (IOException e) {
			System.err.println("Error while reading file '" + args[0] + "' : "
					+ e.getMessage());
			System.exit(-1);
		}

		simulator.execute();
	}
}
