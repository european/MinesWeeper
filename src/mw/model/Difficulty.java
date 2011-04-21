package mw.model;

public enum Difficulty {
	EINFACH, MITTEL, SCHWER, BENUTZERDEFINIERT;

	public static Difficulty fromString(String name) {
		return getEnumFromString(Difficulty.class, name);
	}

	public String toString() {
		String ret = "";

		switch (this) {
		case EINFACH:
			ret = "einfach";
			break;
		case MITTEL:
			ret = "mittel";
			break;
		case SCHWER:
			ret = "schwer";
			break;
		case BENUTZERDEFINIERT:
			ret = "benutzerdefiniert";
			break;	
		default:
			break;
		}

		return ret;
	}

	/**
	 * A common method for all enums since they can't have another base class
	 * 
	 * @param <T>
	 *            Enum type
	 * @param c
	 *            enum type. All enums must be all caps.
	 * @param string
	 *            case insensitive
	 * @return corresponding enum, or null
	 */
	public static <T extends Enum<T>> T getEnumFromString(Class<T> c, String string) {
		if (c != null && string != null) {
			try {
				return Enum.valueOf(c, string.trim().toUpperCase());
			} catch (IllegalArgumentException ex) {
			}
		}

		return null;
	}

}