public class Estudiante {
        private String nombre;
        private String id;

        // Constructor
        public Estudiante(String nombre, String id) {
            this.nombre = nombre;
            this.id = id;
        }
        //Obtener informacion
        public String getNombre() {
            return nombre;
        }
        public String getId() {
            return id;
        }
        public void setid(String id) {
            this.id = id;
        }
        @Override
        public String toString() {
            return "Estudiante{"
                    + "nombre=" + nombre +
                    ", id=" + id +
                    '}';
        }
}
