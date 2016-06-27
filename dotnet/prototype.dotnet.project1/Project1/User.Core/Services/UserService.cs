using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using User.Core.Models.PO;
using User.Core.Repositories;
using User.Core.Service;

namespace User.Core.Services
{
    public class UserService : IUserService
    {
        private IUserRepository userRepository;

        public UserService(IUserRepository userRepository)
        {
            this.userRepository = userRepository;
        }

        public UserPO GetUser(long userId)
        {
            UserPO user = userRepository.getUser(userId);

            if (user == null)
                throw new Exception("User not found.");

            return user;
        }

        public bool CheckUserEmail(string email)
        {
            Regex regex;
            Match match;

            if (!string.IsNullOrEmpty(email))
            {
                regex = new Regex(@"^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$");
                match = regex.Match(email);
                return match.Success;
            }
            else
            {
                throw new ArgumentException("Argument email is null or empty.");
            }
        }

        public bool LoginWithEmail(string email, string password)
        {
            return false;
        }

        public bool LoginWithUsername(string username, string password)
        {
            return false;
        }
    }
}
