using System;
using System.Text;

namespace KeyNumberGenerator
{
    class KeyNumberGenerator
    {
        int year = -1;
        public bool yearReady;

        public static string[] markets = { "AUG", "BAL", "BEG", "BEN", "BER", "BOW", "BUN", "BUR", "CAI", "DAR", "DEV", "GEE", "GLA", "GOL", "HOB", "IPS", "LAU", "LIN", "MAC",
            "MAR", "MIL", "MUR", "MUS", "NOW", "PIR", "QUE", "ROC", "TOW", "WOL", "TRSN", "AGEN" };
        public bool marketReady = false;
        string market;

        string writerInitial;
        string clientInitial;

        public static int[] durations = { 10, 15, 30, 45, 60, 90 };
        public bool durationReady = false;
        int duration = -1;

        public static string[] types = { "R", "L", "SL", "X", "SX" };
        public bool typeReady = false;
        string type;

        static int number = 0;
        public string keyNumber;

        public KeyNumberGenerator()
        {
            number = Properties.Settings.Default.number;
        }

        public void SetMarket(string input)
        {
            if (input == "NULL")
            {
                this.market = null;
                this.marketReady = false;
                Console.WriteLine("SetMarket" + market);
            }
            else
            {
                try
                {
                    int checkInput = Array.IndexOf(markets, input);
                    this.market = markets[checkInput];
                    this.marketReady = true;
                    Console.WriteLine("SetMarket: " + market + " - Successful!");
                }
                catch (IndexOutOfRangeException)
                {
                    Console.WriteLine("SetMarket: " + input + " - Invalid.");
                }
            }
        }

        public void SetYear(String input)
        {
            string currentYear = DateTime.Now.Year.ToString();
            Console.WriteLine("Current year: " + currentYear);
            try
            {
                if (input.Length == 2 && int.Parse(input) >= int.Parse(CharConcatenation.Concat(currentYear[2], currentYear[3])))
                {
                    this.year = int.Parse(input);
                    this.yearReady = true;
                    Console.WriteLine("SetYear1: " + this.year + " - Successful!");
                }
                else if (input.Length == 4 && int.Parse(input) >= int.Parse(currentYear))
                {
                    char yearFirstChar = input[2];
                    char yearSecondChar = input[3];
                    this.year = int.Parse(CharConcatenation.Concat(yearFirstChar, yearSecondChar));
                    Console.WriteLine("SetYear2: " + this.year + " - Successful!");
                    this.yearReady = true;
                }
                else
                {
                    this.yearReady = false;
                    Console.WriteLine("SetYear: " + input + " - Invalid.");
                }
            }
            catch (NullReferenceException)
            {
                Console.WriteLine("SetYear: null");
                year = -1;
                this.yearReady = false;
            }
            Console.WriteLine(yearReady);
        }

        public void SetWriterI(string input)
        {
            if (input == "")
            {
                writerInitial = null;
                Console.WriteLine("SetWriterI: null");
            }
            else if (input == "Type name")
            {
                writerInitial = null;
                Console.WriteLine("SetWriterI: null");
            }
            else
            {
                try
                {
                    string upperInput = input.ToUpper();
                    int firstSpace = upperInput.IndexOf(' ');
                    int secondSpace = upperInput.IndexOf(' ', firstSpace + 1);
                    char firstInitial = upperInput[0];
                    char secondInitial;
                    char thirdInitial;
                    if (firstSpace != -1)
                    {
                        secondInitial = upperInput.Substring(firstSpace + 1)[0];
                        if (secondSpace != -1)
                        {
                            thirdInitial = upperInput.Substring(secondSpace + 1)[0];
                            writerInitial = CharConcatenation.Concat(firstInitial, secondInitial, thirdInitial);
                        }
                        else
                        {
                            writerInitial = CharConcatenation.Concat(firstInitial, secondInitial);
                        }
                    }
                    else
                    {
                        writerInitial = firstInitial.ToString();
                    }
                    Console.WriteLine("SetWriterI: " + writerInitial + " - Successful!");
                }
                catch (NullReferenceException)
                {
                    writerInitial = null;
                    Console.WriteLine("SetWriterI: null");
                }
            }
        }

        public void SetDuration(int input)
        {
            if (input == -1)
            {
                duration = -1;
                durationReady = false;
                Console.WriteLine("SetDuration: null");
            }
            else
            {
                try
                {
                    int checkInput = Array.IndexOf(durations, input);
                    duration = durations[checkInput];
                    durationReady = true;
                    Console.WriteLine("SetDuration: " + duration + " - Successful!");
                }
                catch (IndexOutOfRangeException)
                {
                    durationReady = false;
                    Console.WriteLine("SetDuration: " + input + " - Invalid.");
                }
            }
        }

        public void SetType(string input)
        {
            if (input == null)
            {
                type = null;
                typeReady = false;
                Console.WriteLine("SetType: null");
            }
            else
            {
                try
                {
                    if (input.Equals("Cross"))
                    {
                        type = types[3];
                        Console.WriteLine("SetType: " + type + " - Successful!");
                    }
                    else if (input.Equals("Sim Cross"))
                    {
                        type = types[4];
                        Console.WriteLine("SetType: " + type + " - Successful!");
                    }
                    else
                    {
                        string correctedInput = input.Replace("Sim", " ").Trim();
                        Console.WriteLine("SetTypePre: " + correctedInput + "| SetTypePost: " + correctedInput[0]);
                        int checkInput = Array.IndexOf(types, correctedInput[0].ToString());
                        type = types[checkInput];
                        typeReady = true;
                        Console.WriteLine("SetType: " + type + " - Successful!");
                    }

                }
                catch (IndexOutOfRangeException)
                {
                    typeReady = false;
                    Console.WriteLine("SetType: " + input + " - Invalid.");
                }
            }
        }

        public void SetClientI(string input)
        {
            if (input == "")
            {
                clientInitial = null;
                Console.WriteLine("SetClientI: null");
            }
            else if (input == "Type client name")
            {
                clientInitial = null;
                Console.WriteLine("SetClientI: null");
            }
            else
            {
                try
                {
                    string upperInput = input.ToUpper();
                    int firstSpace = upperInput.IndexOf(' ');
                    int secondSpace = upperInput.IndexOf(' ', firstSpace + 1);
                    int thirdSpace = upperInput.IndexOf(' ', secondSpace + 1);
                    char firstInitial = upperInput[0];
                    char secondInitial;
                    char thirdInitial;
                    char fourthInitial;

                    if (firstSpace != -1)
                    {
                        secondInitial = upperInput.Substring(firstSpace + 1)[0];
                        if (secondSpace != -1)
                        {
                            thirdInitial = upperInput.Substring(secondSpace + 1)[0];
                            if (thirdSpace != -1)
                            {
                                fourthInitial = upperInput.Substring(thirdSpace + 1)[0];
                                clientInitial = CharConcatenation.Concat(firstInitial, secondInitial, thirdInitial, fourthInitial);
                            }
                            else
                            {
                                clientInitial = CharConcatenation.Concat(firstInitial, secondInitial, thirdInitial);
                            }
                        }
                        else
                        {
                            clientInitial = CharConcatenation.Concat(firstInitial, secondInitial);
                        }
                    }
                    else
                    {
                        clientInitial = firstInitial.ToString();
                    }
                    /*
                    if (!input.Contains(" "))
                    {
                        if (input.Length >= 2)
                        {
                            char clientFirstInitial = upperInput[0];
                            char clientSecondInitial = upperInput[1];
                            clientInitial = CharConcatenation.Concat(clientFirstInitial, clientSecondInitial);
                        }
                        else
                        {
                            clientInitial = upperInput[0].ToString();
                        }
                    }
                    else
                    {
                        int secondInitialIndex = input.ToUpper().IndexOf(" ") + 1;
                        char clientFirstInitial = input.ToUpper()[0];
                        char clientSecondInitial = input.ToUpper()[secondInitialIndex];

                        clientInitial = CharConcatenation.Concat(clientFirstInitial, clientSecondInitial);
                    }
                    */
                    Console.WriteLine("SetClientI: " + clientInitial + " - Successful!");
                }
                catch (NullReferenceException)
                {
                    clientInitial = null;
                    Console.WriteLine("SetClientI: null");
                }
            }
         }

        public string Get(string input)
        {
            if (input != null)
            {
                return input;
            }
            else
            {
                return "NULL";
            }
        }
        public string Get(int input)
        {
            if (input != -1)
            {
                return Convert.ToString(input);
            }
            else
            {
                return "NULL";
            }
        }
        public string Get(char input)
        {
            if (input != '\0')
            {
                return Convert.ToString(input);
            }
            else
            {
                return "NULL";
            }
        }

        public string Generate()
        {
            Console.WriteLine("Key Number:");
            keyNumber = Get(market) + Get(year) + Get(writerInitial) + '-' + Get(clientInitial) + '-' + Get(duration) + Get(type) + '-' + number;
            Console.WriteLine(keyNumber);
            number++;
            Properties.Settings.Default.number = number;
            Properties.Settings.Default.Save();

            System.Windows.Forms.Clipboard.SetText(keyNumber);

            return keyNumber;
        }

        public void Save(string property)
        {
            if (property == "market")
            {
                Properties.Settings.Default.market = market;
                Properties.Settings.Default.Save();
            }
            else if (property == "year")
            {
                Properties.Settings.Default.year = year.ToString();
                Properties.Settings.Default.Save();
            }
            else if (property == "writerInitial")
            {
                Properties.Settings.Default.writerInitial = writerInitial.ToString();
                Properties.Settings.Default.Save();
            }
            else if (property == "duration")
            {
                Properties.Settings.Default.duration = duration;
                Properties.Settings.Default.Save();
            }
            else if (property == "type")
            {
                Properties.Settings.Default.type = type;
                Properties.Settings.Default.Save();
            }
            else if (property == "clientInitial")
            {
                Properties.Settings.Default.clientInitial = clientInitial;
                Properties.Settings.Default.Save();
            }
            else if (property == "number")
            {
                Properties.Settings.Default.number = number;
                Properties.Settings.Default.Save();
            }
        }

        public string Load(string property)
        {
            if (property == "market")
            {
                return Properties.Settings.Default.market;
            }
            else if (property == "year")
            {
                return Properties.Settings.Default.year;
            }
            else if (property == "writerInitial")
            {
                return Properties.Settings.Default.writerInitial;
            }
            else if (property == "duration")
            {
                return Properties.Settings.Default.duration.ToString();
            }
            else if (property == "type")
            {
                return Properties.Settings.Default.type;
            }
            else if (property == "clientInitial")
            {
                return Properties.Settings.Default.clientInitial;
            }
            else if (property == "number")
            {
                return Properties.Settings.Default.number.ToString();
            }
            else
            {
                return "NULL";
            }
        }

    }

    static class CharConcatenation
    {
        public static String Concat(params char[] chars)
        {
            if (chars.Length == 0)
            {
                return "";
            }
            StringBuilder s = new StringBuilder(chars.Length);
            foreach (char c in chars)
            {
                s.Append(c);
            }
            return s.ToString();
        }
    }
}
