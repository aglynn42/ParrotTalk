package club.callistohouse.session.protocol;

import org.apache.log4j.Logger;

import club.callistohouse.session.payload.Frame;
import club.callistohouse.session.payload.RawData;
import club.callistohouse.utils.events.Listener;
import club.callistohouse.utils.transport.NIOConnection;
import club.callistohouse.utils.transport.NIOConnection.DataReceived;

public class SocketThunk extends ThunkLayer {
	private static Logger log = Logger.getLogger(SocketThunk.class);

	private ThunkStack stack;
	private NIOConnection connection;

	public SocketThunk(ThunkStack stack, NIOConnection conn) {
		this.stack = stack;
		this.connection = conn;
		connection.addListener(new Listener<DataReceived>(DataReceived.class) {
			@Override
			protected void handle(DataReceived event) {
				upcall(new Frame(new RawData(), event.data));
			}
		});
	}

	public boolean isConnected() {
		return connection != null;
	}

	@Override
	public void close() {
		super.close();
		connection.stop(null);
		connection = null;
	}

	public byte[] getData() {
		return null;
	}

	public void downcall(Frame frame) {
//		log.debug("socket sending: " + frame);
		try {
			connection.send((byte[]) frame.toByteArray());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void upcall(Frame frame) {
		stack.upcall(frame, this);
	}
}
