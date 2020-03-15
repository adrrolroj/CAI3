
package desarrollo2.Reporting;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import desarrollo2.DaemonExecutionMode;

/**
 * <p>
 * Encapsula un incidente.
 * </p>
 *
 * <p>
 * El método <code>toString()</code> produce una cadena en el formato usado por el archivo
 * de incidentes para un incidente.
 * </p>
 */
public class Incident {

	/** Descripción corta de los incidentes para incluir en el archivo de incidentes. */
	private static final Map<DaemonExecutionMode.IncidentCode, String> incidentCodeDescriptions;
	static {
		incidentCodeDescriptions = new HashMap<>();
		Incident.incidentCodeDescriptions.put(DaemonExecutionMode.IncidentCode.NO_ERROR, "No incident");
		Incident.incidentCodeDescriptions.put(DaemonExecutionMode.IncidentCode.MISSING_FILEHASH, "The following file is listed in the configuration file but not in the hashes file.");
		Incident.incidentCodeDescriptions.put(DaemonExecutionMode.IncidentCode.MISSING_FILE, "The following file is listed in the configuration file but is not found in the system.");
		Incident.incidentCodeDescriptions.put(DaemonExecutionMode.IncidentCode.HASH_MISSMATCH, "The following file's actual hash does not match the stored hash.");
		Incident.incidentCodeDescriptions.put(DaemonExecutionMode.IncidentCode.UNKNOWN_FILE, "The following file appears in the hash list but is not listed in the configuration file.");
	}

	private LocalDateTime						timestamp;
	private DaemonExecutionMode.IncidentCode	incidentType;
	private String								filePath;


	/**
	 * <p>
	 * Crea un nuevo objeto incidente.
	 * </p>
	 *
	 * <p>
	 * El <i>timestamp</i> será el momento actual.
	 * </p>
	 *
	 * @param incidentType
	 *            Código de incidente.
	 * @param filePath
	 *            Ruta al archivo relativo al incidente.
	 */
	public Incident(final DaemonExecutionMode.IncidentCode incidentType, final String filePath) {
		this.timestamp = LocalDateTime.now();
		this.incidentType = incidentType;
		this.filePath = filePath;
	}

	/**
	 * <p>
	 * Crea un nuevo objeto incidente.
	 * </p>
	 *
	 * @param timestamp
	 *            <p>
	 *            Momento del incidente.
	 *            </p>
	 * @param incidentCode
	 *            <p>
	 *            Código del incidente.
	 *            </p>
	 * @param filePath
	 *            <p>
	 *            Ruta al archivo relativo al incidente.
	 *            </p>
	 */
	public Incident(final LocalDateTime timestamp, final DaemonExecutionMode.IncidentCode incidentCode, final String filePath) {
		this.timestamp = timestamp;
		this.incidentType = incidentCode;
		this.filePath = filePath;
	}

	public LocalDateTime getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(final LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public DaemonExecutionMode.IncidentCode getIncidentType() {
		return this.incidentType;
	}

	public void setIncidentType(final DaemonExecutionMode.IncidentCode incidentCode) {
		this.incidentType = incidentCode;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(final String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String toString() {
		String rval = "";

		rval += "[" + this.timestamp.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "]";
		rval += "[" + this.incidentType.toString() + "]";
		rval += Incident.incidentCodeDescriptions.get(this.incidentType) + "\n";
		rval += this.filePath + "\n";

		return rval;
	}
}
