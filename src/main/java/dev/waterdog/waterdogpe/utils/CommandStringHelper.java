/*
 * Copyright 2025 WaterdogTEAM
 * Licensed under the GNU General Public License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.waterdog.waterdogpe.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandStringHelper {


    public static String[] parseQuoteAware(String commandLine) {
        List<String> args = new ArrayList<>();
        Pattern pattern = Pattern.compile("\"((?:\\\\.|[^\\\\\"])*)\"|(\\S+)");
        Matcher matcher = pattern.matcher(commandLine);

        while (matcher.find()) {
            String match = null;
            if (matcher.group(1) != null) {
                match = matcher.group(1);
            } else if (matcher.group(2) != null) {
                match = matcher.group(2);
            }

            if (match != null) {
                // Remplacer les séquences échappées \" ou \\ par " ou \
                match = match.replaceAll("\\\\([\\\\\"])","$1");
                args.add(match);
            }
        }

        return args.toArray(new String[0]);
    }
}
