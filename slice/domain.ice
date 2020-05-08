// Custom package mapping
["java:package:cl.ucn.disc.pdis.lab.zeroice"]
module model
{
    // The API
    interface Engine
    {
        string getDate();

        bool getDigitoVerificador(string rut);
        bool validateRut(string rut);
    }

}
