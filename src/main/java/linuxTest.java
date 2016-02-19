import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class linuxTest {
	public static void main(String arg[]) throws IOException,
			InterruptedException {

		System.out.print("1233455566666");

		try {
			String[] cmdA = {"echo 'hello'" };
			Process process = Runtime.getRuntime().exec(cmdA);

			InputStreamReader ir = new InputStreamReader(
					process.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);

			String line;
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
		} catch (java.io.IOException e) {
			System.err.println("IOException " + e.getMessage());
		}

	}
}
