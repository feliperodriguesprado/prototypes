using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using User.Core.Models.PO;

namespace User.Core.Service
{
    public interface IUserService
    {
        UserPO GetUser(long userId);

        bool CheckUserEmail(string email);

        bool LoginWithEmail(string email, string password);

        bool LoginWithUsername(string username, string password);
    }
}
