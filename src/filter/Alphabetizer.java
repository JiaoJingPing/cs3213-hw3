package filter;

import java.util.Comparator;
import java.util.PriorityQueue;

import msg.EofMessage;
import msg.Message;
import pipe.Pipe;
import util.StringComparator;

public class Alphabetizer extends InputFilter {
	Comparator<String> comparator = new StringComparator();

	PriorityQueue<String> sortedTitles = new PriorityQueue<String>(1,
			comparator);

	public Alphabetizer(Pipe inPipe, Pipe outPipe) {
		super(inPipe, outPipe);
	}

	public void run() {
		assert (sortedTitles != null);
		assert (sortedTitles.isEmpty());

		Message val = inPipe.read();
		// TODO fix this!

		if (!(val instanceof EofMessage)) {
			sortedTitles.add(val.getContent());
		} else {
			while (!sortedTitles.isEmpty()) {
				outPipe.write(new Message(sortedTitles.poll()));
			}
			outPipe.write(new msg.EofMessage());
		}

	}

}
