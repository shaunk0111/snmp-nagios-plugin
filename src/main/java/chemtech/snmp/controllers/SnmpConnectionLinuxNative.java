package chemtech.snmp.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import chemtech.snmp.models.SnmpConfigModel;

public class SnmpConnectionLinuxNative implements SnmpConnectionController {

        SnmpConfigModel config;

        public SnmpConnectionLinuxNative(SnmpConfigModel config) {
                this.config = config;
        }

        @Override
        public String getResponse(String oid) throws Exception {

                Process process;
                BufferedReader reader;

                String command = buildCommand(config.getCommunity(),config.getIP(),oid);
                process = Runtime.getRuntime().exec(command);
                process.waitFor();
                reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String output = reader.readLine();
                output = output.replaceAll("^\"|\"$", ""); // Remove char " from string
                output = output.trim(); // Remove char empty 
                return output;
        }

        /**
         * snmpget Linux Native external command
         * @param community
         * @param host
         * @param oid
         * @return command String
         */
        private String buildCommand(String community, String host, String oid) {

                return "snmpget -Oqv -v2c -c " + community + " " + host + " " + oid;
        }

}

